package com.example.learnmore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val login = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)
        val password2 = findViewById<EditText>(R.id.password2)
        val name = findViewById<EditText>(R.id.name)
        val language = findViewById<EditText>(R.id.language)

        val button = findViewById<Button>(R.id.btn_reg)

        var user: Users;

        button.setOnClickListener(View.OnClickListener {
            if(password.text.toString() == password2.text.toString() && login.text.toString() != "" && password.text.toString() != "" && language.text.toString() != "") {
                user = Users(
                    1,
                    name.text.toString(),
                    language.text.toString(),
                    login.text.toString(),
                    password.text.toString())
                addRequestServer(user)
            }
        })
    }

    private fun addRequestServer(user: Users){
        val retrofitService = RetrofitService()
        val api = retrofitService.getRetrofit()!!.create((Api::class.java))
        api.save(user)!!.enqueue(object : Callback<Users?> {
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                Toast.makeText(this@Registration, "Save successful!", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<Users?>, t: Throwable) {
                Toast.makeText(this@Registration, "Save failed!$t", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
}