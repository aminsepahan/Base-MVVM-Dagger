package com.amin.homitel.utils

import android.app.Application
import android.content.Context
import com.amin.homitel.R
import com.amin.homitel.base.App
import org.mockito.Mockito

const val STRING_UNKNOWN_ERROR = "STRING_UNKNOWN_ERROR"

/**
 * Returns a mocked Context for the unit tests.
 * @return a mocked Context for the unit tests
 */
fun getTestContext(): Context {
    val application: App = Mockito.mock(App::class.java)
    Mockito.`when`(application.getString(R.string.unknown_error)).thenReturn(STRING_UNKNOWN_ERROR)
    Mockito.`when`(application.applicationContext).thenReturn(application)
    return application
}