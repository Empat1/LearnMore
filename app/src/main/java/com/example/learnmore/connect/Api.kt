package com.example.learnmore.connect

import com.example.learnmore.data.model.Dictionary
import com.example.learnmore.data.model.Users
import com.example.learnmore.data.model.Word
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @GET("/users/get-all")
    fun getAllProduct(): Call<List<Users>>

    @GET("users/authorization/{login}/{password}")
    fun authorization(@Path("login") login:String, @Path("password") password:String):Call<Users?>

    @POST("/users/save")
    fun save(@Body employee: Users?): Call<Users?>?

    @POST("/users/save_this_user")
    fun saveThisUser(@Body employee: Users?) :Call<Users?>?

    @GET("/language/get-all")
    fun getAllLanguage(): Call<List<Language>>

    @GET("/sub/get-all")
    fun getAllSub(): Call<List<Dictionary>>

    @GET("/language/get/{users_edu_language}/{users_login}")
    fun getLanguage(@Path("users_learn_language") learn_language:String , @Path("users_login") users_login:Int): Call<List<Dictionary>>

    @GET("/language-dictionary/{language_name}")
    fun getDictionary(@Path("language_name") language:String) :Call<List<Dictionary>>

    @GET("/words/{language_id}/{dictionary_id}")
    fun getWords(@Path("language_id") language_id :Int, @Path("dictionary_id")dictionary_id :Int): Call<List<Word>>

    @POST("/word/save/{dictionary_id}")
    fun saveWord(@Body word: Word, @Path("dictionary_id") dictionary_id: Int) : Call<Word>
}