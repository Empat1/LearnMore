package com.example.learnmore.ui.readCard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.learnmore.R
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Learn
import com.example.learnmore.data.model.Word
import retrofit2.Call
import retrofit2.Response
import kotlin.random.Random

class WriteCardFragment : Fragment() { //сам экран

    private var readCardViewModel: ReadCardViewModel? = null

    private var wordText: TextView? = null
    private var wordTranslate: EditText? = null
    private lateinit var thisWord: Word
    private lateinit var wordTextCheck: TextView
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_write_card, container, false)
        readCardViewModel = ViewModelProvider(this)[ReadCardViewModel::class.java] //ищем модель

        wordText = view.findViewById(R.id.word_text) //Связываем XML и KT
        wordTranslate = view.findViewById(R.id.word_translate)
        val wordNext = view.findViewById<Button>(R.id.nextWord)
        val wordCheck = view.findViewById<Button>(R.id.checkWord)
        wordTextCheck = view.findViewById(R.id.word_check)


        readCardViewModel!!.getWord().observe(viewLifecycleOwner){
            thisWord = it[Random.nextInt(0 , it.size)]
            wordText!!.text = thisWord.word_text
        }


        wordNext.setOnClickListener{
            knowWord() //незнаю слово
        }

        wordCheck.setOnClickListener {
            checkWord(wordTranslate!!.text.toString())
        }


        return view
    }

    private fun knowWord(){
        wordTextCheck.text = thisWord.word_translate
        wordTextCheck.visibility = View.VISIBLE
    }

    private fun newWord(){

        val wordDate = readCardViewModel!!.getWord().value
        thisWord = wordDate!![Random.nextInt(0 , wordDate.size)]
        wordText!!.text = thisWord.word_text
    }


    var attempt = 0
    private fun checkWord(translate: String) {


        if (translate == thisWord.word_translate) {
            learnWord()
        } else {
            wordTextCheck.text = thisWord.word_translate
            wordTextCheck.visibility = View.VISIBLE
            attempt++
        }
    }

    private fun learnWord(){
        wordTranslate!!.text.clear()
        newWord()
        wordTextCheck.visibility = View.GONE
        if(attempt == 0 ){
            gatLearn(1 , 1)
        }else{
            gatLearn(1 , 10)
        }
        attempt = 0
    }


    public fun gatLearn(userId : Int, wordId : Int){
        val retrofitService = RetrofitService()
        val productAPi = retrofitService.getRetrofit()!!.create(Api::class.java)


        productAPi.getLearn(userId , wordId).enqueue(object : javax.security.auth.callback.Callback,
            retrofit2.Callback<Learn>{
            override fun onResponse(call: Call<Learn>, response: Response<Learn>) {
                println(response.body().toString())
                print("")
            }

            override fun onFailure(call: Call<Learn>, t: Throwable) {
                print(t.toString())
            }

        })
    }
}