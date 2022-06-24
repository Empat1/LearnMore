package com.example.learnmore.ui.readCard

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import com.example.learnmore.R
import com.example.learnmore.data.model.Word

import com.example.learnmore.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyWordsRecyclerViewAdapter(
    private val values: List<Word>,
    val navController: NavController
) : RecyclerView.Adapter<MyWordsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.word_read_item, parent , false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.text.text = values[position].word_text
       holder.translate.text = values[position].word_translate

//        holder.button.setOnClickListener(View.OnClickListener {
//            navController.navigate(R.id.navigation_read_all_card)
//        })
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.dictionary_text)
        val translate = itemView.findViewById<TextView>(R.id.dictionary_translate)
//        val button = itemView.findViewById<Button>(R.id.button_edit)
    }

}