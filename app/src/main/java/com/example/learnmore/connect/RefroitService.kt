package com.example.learnmore.connect

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService { //connecting the application and server
    private var retrofit: Retrofit? = null
    private fun initalizeRetrofin() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.104:8080") //Ip сервера
            .addConverterFactory(GsonConverterFactory.create(Gson())) //чтение JSON
            .build()
    }

    fun getRetrofit(): Retrofit? {
        return retrofit
    }

    init {
        initalizeRetrofin()
    }
}