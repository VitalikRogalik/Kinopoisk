package com.example.kinopoisk

import android.app.Application
import android.content.Context

class MovieApplication: Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate(){
        super.onCreate()
        context = this
    }
}