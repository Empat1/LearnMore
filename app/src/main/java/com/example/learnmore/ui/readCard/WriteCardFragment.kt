package com.example.learnmore.ui.readCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.learnmore.R
import com.example.learnmore.data.model.Word
import kotlin.random.Random

class WriteCardFragment : Fragment() { //сам экран

    var readCardViewModel: ReadCardViewModel? = null

    var wordText: TextView? = null;
    var wordTranslate: EditText? = null
    lateinit var thisWord: Word
    lateinit var wordTextCheck: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_write_card, container, false)
        readCardViewModel = ViewModelProvider(this).get(ReadCardViewModel::class.java) //ищем модель

        wordText = view.findViewById<TextView>(R.id.word_text) //Связываем XML и KT
        wordTranslate = view.findViewById<EditText>(R.id.word_translate)
        val wordNext = view.findViewById<Button>(R.id.nextWord)
        val wordCheck = view.findViewById<Button>(R.id.checkWord)
        wordTextCheck = view.findViewById<TextView>(R.id.word_check)


        readCardViewModel!!.getWord().observe(viewLifecycleOwner){
            thisWord = it.get( Random.nextInt(0 , it.size))
            wordText!!.text = thisWord.word_text
        }


        wordNext.setOnClickListener(View.OnClickListener {
            knowWord() //незнаю слово
        })

        wordCheck.setOnClickListener(View.OnClickListener {
            checkWord(wordTranslate!!.text.toString());
        })


        return view
    }

    fun knowWord(){
        wordTextCheck!!.text = thisWord.word_translate;
        wordTextCheck.visibility = View.VISIBLE
    }

    fun newWord(){

        val wordDate = readCardViewModel!!.getWord().value;
        thisWord = wordDate!!.get(Random.nextInt(0 , wordDate.size))
        wordText!!.text = thisWord.word_text
    }

    fun checkWord(translate: String){
        if(translate == thisWord.word_translate){
            wordTranslate!!.text.clear();
            newWord()
            wordTextCheck.visibility = View.GONE
        }else{
            wordTextCheck!!.text = thisWord.word_translate;
            wordTextCheck.visibility = View.VISIBLE
        }
    }
}