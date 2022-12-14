package com.example.learnmore.ui.account

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learnmore.R
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Language
import com.example.learnmore.data.model.Model
import com.example.learnmore.data.model.Users
import com.example.learnmore.databinding.FragmentAccountBinding
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.XWPFDocument
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class AccountFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentAccountBinding? = null

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
        val spLanguage = binding.spAccountLearnLanguage


        val btStatistic = binding.btnStatistic
        val btSave = binding.btnSave

        btSave.setOnClickListener(View.OnClickListener {
            val us = Model.user!!
            us.user_name = tvAccount.text.toString()
            us.user_language = tvLogin.text.toString()
            us.user_language = tvLanguage.text.toString()
            addRequestServer(us)
        })

        btStatistic.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_statisticsFragment)
        })

        if(Model.user != null) {
            val us = Model.user!!
            Log.i(TAG , "Account to ${us}")
            tvAccount.setText(us.user_name)
            tvLogin.setText(us.user_login)
            tvLanguage.setText(us.user_language)

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item ,strLanguage(us.languages!!))
            spLanguage.adapter = adapter;
            spLanguage.onItemSelectedListener = this
        }
        return root
    }

    fun strLanguage(

        languages:List<Language>): ArrayList<String>{
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
        if(p1 != null && findNavController().currentBackStackEntry != null)

        findNavController().clearBackStack(R.id.navigation_dashboard)
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