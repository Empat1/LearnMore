package com.example.learnmore.ui.readCard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Model
import com.example.learnmore.data.model.Word
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ReadCardViewModel : ViewModel() {

    private val _loadWord = MutableLiveData<List<Word>>().apply {
        val dictionary_id =  Model.dictionary!!.id_dictionary!!
        val language_id = Model.language!!.language_id!!

        val retrofitService = RetrofitService()
        val productApi = retrofitService.getRetrofit()!!.create(Api::class.java)
        productApi.getWord(language_id, dictionary_id).enqueue(object : Callback,
            retrofit2.Callback<List<Word>>{
            override fun onResponse(call: Call<List<Word>>, response: Response<List<Word>>) {
                println(response.body())
                value = response.body()
            }

            override fun onFailure(call: Call<List<Word>>, t: Throwable) {

            }


        })
    }

    public val words:LiveData<List<Word>> = _loadWord
}