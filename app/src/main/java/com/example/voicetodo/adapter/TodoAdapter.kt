package com.example.voicetodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.voicetodo.R
import com.example.voicetodo.models.Todo

class TodoAdapters(val todos: ArrayList<Todo>): RecyclerView.Adapter<TodoAdapters.TodoViewHolder>() {

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.tvTodoTask.text = todos[position].Task
    }


    class TodoViewHolder(val item: View) : RecyclerView.ViewHolder(item){
        val tvTodoTask = item.findViewById<TextView>(R.id.tvTodoTask)
    }
}