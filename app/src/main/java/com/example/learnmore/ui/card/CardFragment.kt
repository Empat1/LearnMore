package com.example.learnmore.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnmore.databinding.FragmentCardBinding

class CardFragment : Fragment() {

    private var _binding: FragmentCardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var dashboardViewModel : CardViewModel;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(CardViewModel::class.java)

        _binding = FragmentCardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val recyclerView: RecyclerView = binding.dictionary

        val onListItemClick: OnListItemClick = object : OnListItemClick {
            override fun onClick(view: View, position: Int) {
                dashboardViewModel.setDictionary()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false)
        dashboardViewModel.dictionary.observe(viewLifecycleOwner) {
                recyclerView.adapter = DictionaryPVAdapter(it , findNavController())
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.setDictionary()

    }

    fun navigateToDictionaryMenu(language_name:String){
//        val navController = findNavController()
//        val action = DictionaryMenuFragment.
//        navController.navigate(R.id.navigation_dictionary_menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}