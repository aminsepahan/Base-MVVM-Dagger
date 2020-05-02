package com.amin.sample.utils.decoder

import com.amin.sample.ui.decoding.ProcessingDataModel
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt

@Suppress("EXPERIMENTAL_API_USAGE")
class WavDecoder(
    val completed: (ProcessingDataModel) -> Unit
) {

    private var currentProgress = 0
    private val signalsZero = 32768
    private val leadByte = "01111111111"

    suspend fun convertWavSignalsToBinary(
        signals: ByteArray
    ): String {
        var signalDuration = 0
        var signalUpOrDown = signals[40].toInt() + signals[41].toInt() * 256 > signalsZero
        val binaryStream = StringBuilder()
        for (i in 40 until signals.size step 4) {
            val currentDoubleByte =
                signals[i].toUByte().toInt() + signals[i + 1].toUByte().toInt() * 256
            if (currentDoubleByte > signalsZero) {
                if (!signalUpOrDown) {
                    binaryStream.append(processSignal(signalDuration))
                    postUpdate(
                        ProcessingDataModel(
                            binaryStream.last(),
                            currentDoubleByte,
                            "${if (signalUpOrDown) "↑" else "↓"} $signalDuration",
                            '_',
                            (i.toFloat() / signals.size * 10000 / 2).roundToInt()
                        )
                    )
                    signalDuration = 0
                }

                signalUpOrDown = true
            } else {
                if (signalUpOrDown) {
                    binaryStream.append(processSignal(signalDuration))
                    postUpdate(
                        ProcessingDataModel(
                            binaryStream.last(),
                            currentDoubleByte,
                            "${if (signalUpOrDown) "↑" else "↓"} $signalDuration",
                            '_',
                            (i.toFloat() / signals.size * 10000 / 2).roundToInt()
                        )
                    )
                    signalDuration = 0
                }
                signalUpOrDown = false
            }
            signalDuration++
        }
        return binaryStream.toString()

    }

    private fun processSignal(signalDuration: Int) = when {
        signalDuration < 21 -> {
            "1"
        }
        signalDuration < 36 -> {
            "0"
        }
        else -> ""
    }

    fun findLeadOffset(binaryStream: String): Int {
        val offset = binaryStream.indexOf(leadByte)
        var counter = 0
        for (i in offset until binaryStream.length step 11) {
            if (binaryStream.substring(i..i + 10) == leadByte) {
                counter++
                if (counter == 652)
                    return i
            } else
                counter = 0
        }
        return 0
    }

    suspend fun convertBinaryStreamToByteStream(
        binaryStream: String, leadOffset: Int
    ): String {
        val byteStream = StringBuilder()
        for (i in leadOffset until binaryStream.length step 11) {
            var byteData = 0
            for (j in 0..7) {
                if (i + j + 1 < binaryStream.length)
                    byteData += (if (binaryStream[i + 1 + j] == '1') 1 else 0) * 2.0.pow(j).toInt()
            }
            byteStream.append(byteData.toChar())
            postUpdate(
                ProcessingDataModel(
                    '_',
                    -1,
                    "_",
                    byteStream.last(),
                    (i.toFloat() / binaryStream.length * 10000 / 2).roundToInt() + 5000
                )
            )
        }
        return byteStream.toString()
    }

    fun getMessages(bytes: String): List<String> {
        val messages = mutableListOf<String>()
        for (i in 0 until 64) {
            messages.add(bytes.substring(i * 30 + (i), i * 30 + 30 + (i)))
        }
        return correctWordsInMessages(messages)
    }

    private fun correctWordsInMessages(messages: MutableList<String>): List<String> {
        for (i in messages.indices) {
            messages.getOrNull(i + 1)?.takeIf { nextMessage ->
                nextMessage.first() != ' ' && nextMessage.isNotEmpty()
            }?.takeIf { messages[i].isNotEmpty() && messages[i].last() != ' ' }?.let {
                if (messages[i + 1].contains(' ')) {
                    messages[i] = messages[i] + messages[i + 1].substring(
                        0 until messages[i + 1].lastIndexOf(' ')
                    )
                    messages[i + 1] =
                        messages[i + 1].substring(messages[i + 1].lastIndexOf(' ') + 1)
                } else {
                    messages[i] = messages[i] + messages[i + 1]
                    messages[i + 1] = ""
                }
            }
        }
        return messages
    }

    private suspend fun postUpdate(processingDataModel: ProcessingDataModel) {
        if (currentProgress < processingDataModel.progress) {
            completed(processingDataModel)
            delay(if (processingDataModel.progress < 5000) 1 else 5)
        }
        currentProgress = processingDataModel.progress

    }

}