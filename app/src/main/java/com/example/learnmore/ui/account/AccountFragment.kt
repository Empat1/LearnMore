package com.example.learnmore.ui.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.learnmore.R
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Language
import com.example.learnmore.data.model.Model
import com.example.learnmore.data.model.Model.user
import com.example.learnmore.data.model.Users
import com.example.learnmore.databinding.FragmentAccountBinding
import com.example.learnmore.ui.readCard.ReadCardViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val TAG = "AccountFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tvAccount = binding.accoutName
        val tvLogin = binding.accountLogin
        val tvLanguage = binding.accountLaguage
//        val textView = binding.accountLaguage
        val spLanguage = binding.spAccountLearnLanguage

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val btSave = binding.btnSave

        btSave.setOnClickListener(View.OnClickListener {
            val us = Model.user!!
            us.user_name = tvAccount.text.toString()
            us.user_language = tvLogin.text.toString()
            us.user_language = tvLanguage.text.toString()
            addRequestServer(us)
        })

        if(Model.user != null) {
            val us = Model.user!!
            Log.i(TAG , "Account to ${us}")
            tvAccount.setText(us.user_name)
            tvLogin.setText(us.user_language)
            tvLanguage.setText(us.user_language)

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item ,strLanguage(us.languages!!))
            spLanguage.adapter = adapter;
            spLanguage.onItemSelectedListener = this
        }
        return root
    }

    fun strLanguage(languages:List<Language>): ArrayList<String>{
        val arr = ArrayList<String>()
        languages.forEach{arr.add(it.language_name)}
        return arr
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Model.language = Model.user!!.languages!!.get(p2);

        println(Model.language)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun addRequestServer(user: Users){
        val retrofitService = RetrofitService()
        val api = retrofitService.getRetrofit()!!.create((Api::class.java))
        api.saveThisUser(user)!!.enqueue(object : Callback<Users?> {
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
               Log.i(TAG ,"save")
            }

            override fun onFailure(call: Call<Users?>, t: Throwable) {

            }

        })
    }
}