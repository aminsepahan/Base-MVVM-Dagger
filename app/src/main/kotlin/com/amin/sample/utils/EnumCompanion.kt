package com.amin.sample.utils

open class EnumCompanion<T, V>(private val valueMap: Map<T, V>) {
    fun fromInt(type: T) = valueMap[type]
}