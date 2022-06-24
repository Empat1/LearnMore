package com.example.learnmore.ui.readCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learnmore.R

/**
 * A fragment representing a list of Items.
 */
class ReadCardFragment : Fragment() {

    private var columnCount = 1
    var readCardViewModel: ReadCardViewModel? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_read_card, container, false)

        val rvList = view.findViewById<RecyclerView>(R.id.list)

        readCardViewModel = ViewModelProvider(this).get(ReadCardViewModel::class.java)

        readCardViewModel!!.words.observe(viewLifecycleOwner){
            val adapter = MyWordsRecyclerViewAdapter(it , findNavController())
            rvList.adapter = adapter;
        }



        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
////                adapter = MyWordsRecyclerViewAdapter()
//            }
//        }
        return view
    }
}