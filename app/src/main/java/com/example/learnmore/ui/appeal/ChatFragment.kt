package com.example.learnmore.ui.appeal

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import com.example.learnmore.R
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Appeal
import com.example.learnmore.data.model.Model
import retrofit2.Call
import retrofit2.Response
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

public const val argName = "appeal_id";

class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var appealId: Int? = null
    lateinit var appeal: Appeal;
    var text: TextView? = null;
    var answer: TextView? = null;
    var cardAnswer: CardView? = null
    var eSendTest: EditText? = null
    var sendButton: Button? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            appealId = it.getInt(argName);
            println(appealId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_chat, container, false)

        text = view.findViewById<TextView>(R.id.appeal_description)
        answer = view.findViewById<TextView>(R.id.appeal_answer)
        eSendTest = view.findViewById<EditText>(R.id.text_send)
        cardAnswer = view.findViewById(R.id.card_answer)
        sendButton = view.findViewById<Button>(R.id.send)

        initData();


        if(appeal.appeal_id == 0){

        }else{
            text!!.text = appeal.appeal_description

            if(appeal.appeal_answer == "") {
                answer!!.text = "Ожидайте ответа оператора"
            }else{
                answer!!.text = appeal.appeal_answer
            }
        }


        return view
    }

    fun initData(){
        val appeal = getAppealLoadBd(appealId!!)
        if(appeal == null)
            this.appeal = Appeal(0 , 0 , 0 , 0 , Date() , "Не удалось найти обращение" , "")
        else
            this.appeal = appeal;



    }

    fun getAppeal(appealId : Int){
//        val retrofitService = RetrofitService()
//        val productAPi = retrofitService.getRetrofit()!!.create(Api::class.java)
//
//        productAPi.getAppeal(appealId).enqueue(object : javax.security.auth.callback.Callback,
//            retrofit2.Callback<Appeal>{
//            override fun onResponse(call: Call<Appeal>, response: Response<Appeal>) {
//                if(response.body() != null)
//                    appeal.also {   response.body()!!}
//                text!!.text = appeal!!.value!!.appeal_description
//            }
//
//            override fun onFailure(call: Call<Appeal>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })

    }

    fun getAppealLoadBd(appealId : Int) : Appeal?{
        if(Model.appeal != null) {
            Model.appeal!!.forEach { if (it.appeal_id == appealId) return it }
        }
        return null;
    }

}