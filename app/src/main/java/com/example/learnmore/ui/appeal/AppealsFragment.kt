package com.example.learnmore.ui.appeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnmore.R
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Appeal
import com.example.learnmore.data.model.Model
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AppealsFragment : Fragment() {

    private lateinit var rvAppeal: RecyclerView;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_appeals, container, false)

        rvAppeal = view.findViewById(R.id.rv_appeals)
        rvAppeal.layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false)

        initDate()



        return view;
    }

    fun initDate(){

        val userId = Model.user!!.user_id
        getLearn(userId!!)
    }




    fun getLearn(userId : Int){
        val retrofitService = RetrofitService()
        val productAPi = retrofitService.getRetrofit()!!.create(Api::class.java)

        productAPi.getAppealForUser(userId).enqueue(object : javax.security.auth.callback.Callback,
            retrofit2.Callback<List<Appeal>>{
            override fun onResponse(call: Call<List<Appeal>>, response: Response<List<Appeal>>) {
                if(response.body() != null) {
                    Model.appeal = response.body()!!;
                    rvAppeal.adapter = AppealRVAdapter(Model.appeal!!, findNavController())


                }
            }

            override fun onFailure(call: Call<List<Appeal>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }


}

