package com.bignerdranch.android.lab11json

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskInfo : AppCompatActivity() {

    private lateinit var myTask : Button
    private lateinit var addTask : Button
    private lateinit var listTask: MutableList<TaskClass?>

    private val APP = "prefs"
    private lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_info)
        listTask = mutableListOf()
        myTask = findViewById<Button>(R.id.myTaskBtn)
        addTask = findViewById(R.id.addTaskBtn)
        //Подгрузка задач
        prefs = getSharedPreferences(APP, Context.MODE_PRIVATE)
        if(prefs.contains("JSON_STRING")){
            val json:String? = prefs.getString("JSON_STRING", "")
            listTask = Gson().fromJson<MutableList<TaskClass>>(json, object: TypeToken<MutableList<TaskClass>>() {}.type)
                .toMutableList()
            listTask.forEach(){
                Log.d("JSON-INFO", it.toString())
            }
        }



        //Переход в окно добавления
        addTask.setOnClickListener {
            val reDir = Intent(this, MainActivity::class.java)
            startActivity(reDir)
        }
        //Посмотреть задачи
        var json = intent.getStringExtra("JSON").toString()
        val task = Gson().fromJson<TaskClass>(json, object: TypeToken<TaskClass>() {}.type) ?: null
        if(task != null){
            listTask.add(task)
        }
        myTask.setOnClickListener {
            Log.d("JSON-INFO", Gson().toJson(listTask).toString())
        }

    }

    override fun onStop() {
        super.onStop()
        val listJSON = Gson().toJson(listTask).toString()
        val edit = prefs.edit()
        edit.putString("JSON_STRING", listJSON)
        edit.apply()

    }
}