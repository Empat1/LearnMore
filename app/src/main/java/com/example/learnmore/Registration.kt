package com.example.learnmore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.learnmore.connect.Api
import com.example.learnmore.connect.RetrofitService
import com.example.learnmore.data.model.Model
import com.example.learnmore.data.model.Model.user
import com.example.learnmore.data.model.Users
import com.example.learnmore.ui.authorization.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registration : AppCompatActivity() {
    lateinit var login: EditText
    lateinit var password: EditText;
    lateinit var password2: EditText;
    lateinit var name: EditText;
    lateinit var language: EditText;
    lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        login = findViewById<EditText>(R.id.login) //assign data from an xml file to a variable
        password = findViewById<EditText>(R.id.password)
        password2 = findViewById<EditText>(R.id.password2)
        name = findViewById<EditText>(R.id.name)
        language = findViewById<EditText>(R.id.language)

        button = findViewById<Button>(R.id.btn_reg)


        button.setOnClickListener(View.OnClickListener {
            if(lookingAtTheValues()){
                createUserFromDisplay()
            }
        })


    }

    fun createUserFromDisplay(){
        var user: Users;
            //проверка
                user = Users(
                    1,
                    name.text.toString(),
                    language.text.toString(),
                    login.text.toString(),
                    password.text.toString())
                addRequestServer(user)

    }

    fun lookingAtTheValues() = (password.text.toString() == password2.text.toString() && login.text.toString() != "" && password.text.toString()
            != "" && language.text.toString() != "")


    private fun addRequestServer(user: Users){ //saving the user in the database
        val retrofitService = RetrofitService() //Подключение к серверу
        val api = retrofitService.getRetrofit()!!.create((Api::class.java))

        api.save(user)!!.enqueue(object : Callback<Users?> { //callback - Обратный вызов
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                Toast.makeText(this@Registration, "Save successful!", Toast.LENGTH_SHORT) //display a message about the successful saving of the user
                    .show()
                loadUser(user.user_login!! , user.user_password!!)
            }

            override fun onFailure(call: Call<Users?>, t: Throwable) {
                Toast.makeText(this@Registration, "Save failed!$t", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    fun loadUser(login:String , password:String){
        val retrofitService = RetrofitService()
        val productApi = retrofitService.getRetrofit()!!.create(Api::class.java)
        productApi.authorization(login , password).enqueue(object : javax.security.auth.callback.Callback,
            retrofit2.Callback<Users?> {
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                Log.i("Log" , "User auto = ${response.body()}")
                Model.user = response.body();
                if(Model.user != null) {
                    val intent = Intent(this@Registration, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Users?>, t: Throwable) {
                Log.i("Log" , t.toString())
            }


        })
    }
}