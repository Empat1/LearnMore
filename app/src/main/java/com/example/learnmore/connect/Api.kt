package com.example.learnmore.connect

import com.example.learnmore.data.model.*
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @GET("/word_for_learn/{user_id}")
    fun getWordLearn(@Path("user_id") login: Int) : Call<List<Word>>

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

    @GET("learn/{user_id}/{word_id}")
    fun getLearn(@Path("user_id") user_id: Int, @Path("word_id") word_id: Int) : Call<Learn>

    @POST("/learn/save")
    fun saveLearn(@Body learn: Learn?) : Call<Learn?>?

    @GET("/appeal/get-user/{user_id}" )
    fun getAppealForUser(@Path("user_id") user_id: Int) : Call<List<Appeal>>

    @GET("/appeal/{appeal_id}")
    fun getAppeal(@Path("appeal_id") appeal_id: Int): Call<Appeal>
}