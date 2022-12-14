package com.example.learnmore.ui.authorization

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.learnmore.R
import com.example.learnmore.Registration
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Model
import com.example.learnmore.data.model.Users
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActive"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)

        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_reg = findViewById<Button>(R.id.btn_go_reg)

        btn_login.setOnClickListener(View.OnClickListener {
            if(login.text.toString() != "" && password.text.toString() != "");
                loadUser(login.text.toString() , password.text.toString())
        })

        btn_reg.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity , Registration::class.java)
            startActivity(intent)
        })
    }


    fun loadUser(login:String , password:String){
        val retrofitService = RetrofitService()
        val productApi = retrofitService.getRetrofit()!!.create(Api::class.java)
        productApi.authorization(login , password).enqueue(object : Callback,
            retrofit2.Callback<Users?> {
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                Log.i(TAG , "User auto = ${response.body()}")
                Model.user = response.body();
                if(Model.user != null) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Users?>, t: Throwable) {
                Log.i(TAG , t.toString())
            }

        })
    }
}

