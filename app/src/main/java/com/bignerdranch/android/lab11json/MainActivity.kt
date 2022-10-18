package com.bignerdranch.android.lab11json

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var nameTask: EditText
    private lateinit var textTask: EditText
    private lateinit var btnTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTask = findViewById(R.id.nameTask)
        textTask = findViewById(R.id.textTask)
        btnTask = findViewById(R.id.buttonTask)

        //Отправка
        btnTask.setOnClickListener {
            val task = Gson().toJson(TaskClass(nameTask.text.toString(), textTask.text.toString()))
            val intent = Intent(this, TaskInfo::class.java)
            intent.putExtra("JSON", task)
            startActivity(intent)
            Log.d("JSON-INFO","Данные записаны")
        }
    }
}