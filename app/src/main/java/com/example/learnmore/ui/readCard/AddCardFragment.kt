package com.example.learnmore.ui.readCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.learnmore.R
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Word
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCardFragment : Fragment() {

    private val DICTIONARY_ID = "DICTIONARY_ID"
    var dictionaryId:Int = 1;

    fun newInstance(dictionaryId: Int) = AddCardFragment().apply{
        arguments = Bundle(1).apply {
            putInt(DICTIONARY_ID , dictionaryId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        savedInstanceState?.apply {
            dictionaryId = getInt(DICTIONARY_ID)
        }


        val view = inflater.inflate(R.layout.fragment_add_card, container, false)
        val tvWord = view.findViewById<TextView>(R.id.word_text)
        val tvTranslate = view.findViewById<TextView>(R.id.word_translate)
        val btnAdd = view.findViewById<Button>(R.id.saveWord)

        btnAdd.setOnClickListener(View.OnClickListener {
            val text = tvWord.text.toString();
            val translate = tvTranslate.text.toString();
            val word = Word(word_text = text , word_translate = translate , dictionary_id = dictionaryId , word_id = 101);
            addRequestServer(word =  word ,dictionaryId )
        })

        return view
    }

    private fun addRequestServer(word: Word , dictionaryId : Int){
        val retrofitService = RetrofitService()
        val api = retrofitService.getRetrofit()!!.create(Api::class.java)
        api.saveWord(word , dictionaryId).enqueue(object: Callback<Word>{
            override fun onResponse(call: Call<Word>, response: Response<Word>) {
                Toast.makeText(context, "Save successful!", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<Word>, t: Throwable) {
                Toast.makeText(context, "Save failed!$t", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

}