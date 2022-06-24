package com.example.learnmore.connect

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private var retrofit: Retrofit? = null
    private fun initalizeRetrofin() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.103:8080")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    fun getRetrofit(): Retrofit? {
        return retrofit
    }

    init {
        initalizeRetrofin()
    }
}