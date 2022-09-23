package com.example.learnmore.ui.card

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Dictionary
import com.example.learnmore.data.model.Model

import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    private val _sub = MutableLiveData<List<Dictionary>>().apply {
        val retrofitService = RetrofitService()
        val productApi = retrofitService.getRetrofit()!!.create(Api::class.java)
        productApi.getDictionary(Model.language!!.language_name).enqueue(object : Callback,
            retrofit2.Callback<List<Dictionary>> {
            override fun onResponse(
                call: Call<List<Dictionary>>,
                response: Response<List<Dictionary>>
            ) {
                value = response.body();
                println()
            }

            override fun onFailure(call: Call<List<Dictionary>>, t: Throwable) {

            }


        })
    }

    fun setDictionary(){
        dictionary = _sub;

    }


    var dictionary: LiveData<List<Dictionary>> = _sub
    val text: LiveData<String> = _text
}