package com.igorralexsander.common

import com.igorralexsander.common.ApplicationConfigExtensions.getInt
import io.ktor.server.config.*
import java.util.StringJoiner

object ApplicationConfigExtensions {

    fun ApplicationConfig.getString(fieldName:String):String {
        return propertyOrNull(fieldName)?.getString() ?: ""
    }

    fun ApplicationConfig.getInt(fieldName: String):Int {
        return getString(fieldName).toInt()
    }

    fun ApplicationConfig.getLong(fieldName: String):Long{
        return getString(fieldName).toLong()
    }


     fun ApplicationConfig.getFloat(fieldName: String): Float {
        return getString(fieldName).toFloat()
    }
}