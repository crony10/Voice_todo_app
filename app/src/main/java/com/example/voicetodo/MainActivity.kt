package com.example.voicetodo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voicetodo.adapter.TodoAdapters
import com.example.voicetodo.models.Todo
import com.example.voicetodo.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val RQ_SPEECH_REC = 102
    private lateinit var binding : ActivityMainBinding
    val todos: ArrayList<Todo> = ArrayList()
    val todoAdapter = TodoAdapters(todos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        todos.add(Todo("First Task",false))

        binding.rvTodos.layoutManager = LinearLayoutManager(this)

        binding.rvTodos.adapter = todoAdapter

        binding.btnAdd.setOnClickListener{
            val newTodo = binding.etNewTask.text.toString()
            todos.add(Todo(newTodo,false))
            todoAdapter.notifyDataSetChanged();
            binding.etNewTask.text.clear();
        }

        binding.btnVoiceAdd.setOnClickListener{
            askSpeechInput()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RQ_SPEECH_REC && resultCode== Activity.RESULT_OK){
            val result: ArrayList<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val todoResult = result?.get(0).toString()
            todos.add(Todo(todoResult,false))
            Toast.makeText(this,"Successfully added the task!",Toast.LENGTH_SHORT).show()
            todoAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this,"Some error ocurred",Toast.LENGTH_SHORT).show()
        }
    }

    private fun askSpeechInput(){
        if(!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this,"Speech recoginition is not Available",Toast.LENGTH_SHORT).show()
        }else{
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say something")
            startActivityForResult(i,RQ_SPEECH_REC)
        }
    }
}