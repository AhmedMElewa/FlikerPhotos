package com.elewa.flikerphotos.core.keys

class FlikerKeys {

    external fun getBaseUrl(): String
    external fun getApiKey(): String

    init {
        System.loadLibrary("native-lib")
    }
}