package com.example.admin.ui

import android.app.Application
import com.example.admin.data.db.MainDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application() {
    companion object {
        lateinit var mailDB: MainDatabase
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()
        mailDB = MainDatabase.getDatabase(applicationContext)
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.3.4/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}