package com.example.learnmore.ui.appeal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.learnmore.R
import com.example.learnmore.data.model.Appeal
import com.example.learnmore.ui.card.DictionaryPVAdapter

class AppealRVAdapter(var appeals: List<Appeal> , val navController: NavController) : RecyclerView.Adapter<AppealRVAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.appeal_text)
        val cardView = itemView.findViewById<CardView>(R.id.appeal_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.appeal, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = appeals[position].appeal_description
        holder.cardView.setOnClickListener(View.OnClickListener {
            onClick(appeals[position].appeal_id)
        })
    }

    override fun getItemCount() = appeals.size

    fun onClick(id : Int){
        val appealId = id
        val bundle = bundleOf("appeal_id" to appealId)

        navController.navigate(R.id.action_appealsFragment_to_chatFragment , bundle)
    }
}