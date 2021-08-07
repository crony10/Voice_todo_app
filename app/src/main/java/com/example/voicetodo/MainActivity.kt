package com.example.voicetodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.voicetodo.adapter.TodoAdapters
import com.example.voicetodo.models.Todo
import com.example.voicetodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    val todos: ArrayList<Todo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        todos.add(Todo("First Task",false))

        binding.rvTodos.layoutManager = LinearLayoutManager(this)
        val todoAdapter = TodoAdapters(todos)
        binding.rvTodos.adapter = todoAdapter

        binding.btnAdd.setOnClickListener{
            val newTodo = binding.etNewTask.text.toString()
            todos.add(Todo(newTodo,false))
            todoAdapter.notifyDataSetChanged();
        }
    }
}