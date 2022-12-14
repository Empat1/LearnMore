package com.example.learnmore.ui.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController

import androidx.recyclerview.widget.RecyclerView
import com.example.learnmore.R
import com.example.learnmore.data.model.Dictionary
import com.example.learnmore.data.model.Model

class DictionaryPVAdapter(var dictionaries: List<Dictionary> , val navController: NavController) :
    RecyclerView.Adapter<DictionaryPVAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.dictionary_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dictionary, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = dictionaries[position].dictionary_name;
        holder.text.setOnClickListener(View.OnClickListener {
            navigateToDictionaryMenu(dictionaries[position].dictionary_name)
            Model.dictionary = dictionaries[position]
        })



    }

    fun navigateToDictionaryMenu(dictionary_name:String){
//        val action = DictionaryMenuFragment.
        navController.navigate(R.id.action_navigation_dashboard_to_navigation_dictionary_menu)
    }

    override fun getItemCount() = dictionaries.size
}