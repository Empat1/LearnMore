package com.example.learnmore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.learnmore.data.model.Model


class DictionaryMenuFragment : Fragment() {
    private var language: String? = null

    private val TAG = "DictionaryMenuFragment"
//    val args: DictionaryMenuFragmentAgrs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            language = requireArguments().getString(ARG_PARAM1)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dictionary_menu, container, false)

        val cardMemorization = view.findViewById<CardView>(R.id.card_memorization)
        val cardWatchAllCard = view.findViewById<CardView>(R.id.card_watch_all_card)
//        val cardAdd = view.findViewById<CardView>(R.id.card_add_card)
        val cardWriteCard = view.findViewById<CardView>(R.id.card_write_card)
        val tvLearnLanguage = view.findViewById<TextView>(R.id.tv_learn_language)

        tvLearnLanguage.text = "Изучаймый язык = ${Model.language!!.language_name}"

        cardMemorization.setOnClickListener(View.OnClickListener {
            memorization()
        })
        cardWatchAllCard.setOnClickListener(View.OnClickListener {
            watchAllCard()
        })
//        cardAdd.setOnClickListener (View.OnClickListener{
//            addCard()
//        })
        cardWriteCard.setOnClickListener ( View.OnClickListener{
            writeCard()
        } )

        return view;
    }

    companion object {
        private const val ARG_PARAM1 = "language_name"
        fun newInstance(param1: String?, param2: String?): DictionaryMenuFragment {
            val fragment = DictionaryMenuFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }

    private fun memorization(){
        Log.i(TAG , "memorization")
        findNavController().navigate(R.id.action_navigation_dictionary_menu_to_navigation_memorization)

    }

    private fun watchAllCard(){
        Log.i(TAG , "watchAllCard")
        findNavController().navigate(R.id.navigation_read_all_card)
    }

    fun addCard(){
        Log.i(TAG , "AddCard")
        findNavController().navigate(R.id.action_navigation_dictionary_menu_to_fragment_add_card)
    }

    private fun writeCard(){
        Log.i(TAG , "writeCard")
        findNavController().navigate(R.id.action_navigation_dictionary_menu_to_navigation_write_card)
    }
}