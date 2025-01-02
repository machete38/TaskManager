package com.example.taskmanager

class TaskFactory {

    companion object{
        fun newTask(id: Int, title: String, description: String, createdAt: Long): Task
        {
            return Task(id, title, description, createdAt)
        }
    }

}