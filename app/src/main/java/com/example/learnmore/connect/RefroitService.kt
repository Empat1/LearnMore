package com.example.learnmore.connect

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitService { //connecting the application and server
    private var retrofit: Retrofit? = null
    private fun initalizeRetrofin() {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.104:8080") //Ip сервера
            .addConverterFactory(GsonConverterFactory.create(Gson())) //чтение JSON
            .client(client.build())
            .build()
    }

    fun getRetrofit(): Retrofit? {
        return retrofit
    }

    init {
        initalizeRetrofin()
    }
}