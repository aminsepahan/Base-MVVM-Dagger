package com.amin.sample.utils

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

lateinit var MOCK_PATH: String

class ApiUtils {
    companion object {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        inline fun <reified T : Any> getUrl(jsonPath: String): T {
            val buf = StringBuilder()

            val kotlinBuildClassesFolder =
                ApiUtils::class.java.protectionDomain!!.codeSource.location.path
            val assetsPath = kotlinBuildClassesFolder.replace(
                "/build/tmp/kotlin-classes/debugUnitTest/",
                "/src/test/assets/api_mocks/$jsonPath"
            )

            val inputStream = FileInputStream(assetsPath)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            var str: String? = bufferedReader.readLine()
            while (str != null) {
                buf.append(str)
                str = bufferedReader.readLine()
            }
            inputStream.close()
            bufferedReader.close()


            return Gson().fromJson(buf.toString(), T::class.java)!!
        }
    }
}