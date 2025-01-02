package com.example.taskmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var vs: ViewStub
    lateinit var repository: TaskRepository
    var tasks: List<Task>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        initRepository()
  
    }

    private fun initRepository() {
        val taskDao = Room.databaseBuilder(this, TaskDatabase::class.java, "task-database").build().taskDao()
        repository = TaskRepository(taskDao)
        initViewStub(getTasks())
    }

    private fun initViewStub(objectsExist: Boolean) {
        vs.layoutResource = getViewStubLayout(objectsExist)
        vs.inflate()
    }

    private fun getViewStubLayout(objectsExist: Boolean): Int {
        return when {
            objectsExist -> R.layout.task_list
            else -> R.layout.no_tasks
        }
    }

    private fun getTasks(): Boolean {
        tasks = repository.allTasks
        return tasks == null
    }

    private fun initViews() {
        vs = findViewById(R.id.view_stub)
    }
}