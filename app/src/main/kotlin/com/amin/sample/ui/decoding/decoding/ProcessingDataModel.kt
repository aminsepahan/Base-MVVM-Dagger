package com.amin.sample.ui.decoding

data class ProcessingDataModel(
    val currentBit: Char, val currentByte: Int, val currentDirection: String, val currentChar: Char, var progress: Int
)