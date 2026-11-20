package com.bignerdranch.android.zd3_2_moskv

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var email: EditText
lateinit var password: EditText
class SecondActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
    }
    fun Login(view: View){
        if(email.text.toString().isNotEmpty()&&password.text.toString().isNotEmpty()){
            val intent= Intent(this,QuestsActivity::class.java)
            startActivity(intent)
        }
        else{
            val alert= AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("У вас есть пустые поля")
                .setPositiveButton("Ok", null)
                .show()
        }
    }
}