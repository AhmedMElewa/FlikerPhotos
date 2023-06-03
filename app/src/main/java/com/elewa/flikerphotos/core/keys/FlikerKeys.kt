package com.elewa.flikerphotos.core.keys

object FlikerKeys {

    external fun getBaseUrl(): String
    external fun getApiKey(): String

    init {
        System.loadLibrary("native-lib")
    }
}