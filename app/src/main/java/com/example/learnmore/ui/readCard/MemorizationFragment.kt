package com.example.learnmore.ui.readCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.learnmore.R
import kotlin.random.Random

class MemorizationFragment : Fragment() {

    var readCardViewModel :ReadCardViewModel?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_memorization, container, false)
        readCardViewModel = ViewModelProvider(this).get(ReadCardViewModel::class.java)


        val word_text = view.findViewById<TextView>(R.id.word_text)
        val word_translate = view.findViewById<TextView>(R.id.word_translate)
        val wordNext = view.findViewById<Button>(R.id.nextWord)

        readCardViewModel!!.words.observe(viewLifecycleOwner){
            val word = it.get( Random.nextInt(0 , it.size))
            word_text.text = word.word_text
            word_translate.text = word.word_translate
        }

        wordNext.setOnClickListener(View.OnClickListener {
            val wordDate = readCardViewModel!!.words.value;
            val word = wordDate!!.get(Random.nextInt(0 , wordDate.size))
            word_text.text = word.word_text
            word_translate.text = word.word_translate
        })

        return view
    }


}