package com.example.learnmore.ui.readCard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Model
import com.example.learnmore.data.model.Word
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Tag
import javax.security.auth.callback.Callback

class ReadCardViewModel : ViewModel() {

    private var _loadWord = MutableLiveData<List<Word>>()

//    get() = _loadWord.let {
//        startHTTP()
//    }

    fun getWord() :MutableLiveData<List<Word>> {
        startHTTP()
        return _loadWord
    }

//     MutableLiveData<List<Word>>().apply {
//        val dictionary_id =  Model.dictionary!!.id_dictionary!!
//        val language_id = Model.language!!.language_id!!
//
//        val retrofitService = RetrofitService()
//        val productApi = retrofitService.getRetrofit()!!.create(Api::class.java)
//        productApi.getWords(language_id, dictionary_id).enqueue(object : Callback,
//            retrofit2.Callback<List<Word>>{
//            override fun onResponse(call: Call<List<Word>>, response: Response<List<Word>>) {
//                println(response.body())
//                value = response.body()
//            }
//
//            override fun onFailure(call: Call<List<Word>>, t: Throwable) {
//
//            }
//
//
//        })
//    }

//    public var words:LiveData<List<Word>> = _loadWord


    fun startHTTP() {
            val dictionary_id = Model.dictionary!!.id_dictionary!!
            val language_id = Model.language!!.language_id!!

            val retrofitService = RetrofitService()
            val productApi = retrofitService.getRetrofit()!!.create(Api::class.java)

            productApi.getWords(language_id, dictionary_id).enqueue(object : Callback,
                retrofit2.Callback<List<Word>> {
                override fun onResponse(call: Call<List<Word>>, response: Response<List<Word>>) {
                    println(response.body())
                    _loadWord.value = response.body()!!
                }

                override fun onFailure(call: Call<List<Word>>, t: Throwable) {
                     Log.i("" , t.toString())
                }


            })
        }
}