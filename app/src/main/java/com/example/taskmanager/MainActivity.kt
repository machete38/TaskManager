package com.example.taskmanager

import android.os.Bundle
import android.view.ViewStub
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var vs: ViewStub
    private lateinit var repository: TaskRepository
    private var tasks: List<Task> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initRepository()
    }

    private fun initRepository() {
        val database = Room.databaseBuilder(applicationContext, TaskDatabase::class.java, "task-database").build()
        val taskDao = database.taskDao()
        repository = TaskRepository(taskDao)
        loadTasks()
    }

    private fun loadTasks() {
        lifecycleScope.launch {
            tasks = withContext(Dispatchers.IO) {
                repository.getAllTasks()
            }
            initViewStub(tasks.isNotEmpty())
        }
    }

    private fun initViewStub(objectsExist: Boolean) {
        vs.layoutResource = getViewStubLayout(objectsExist)
        vs.inflate()
    }

    private fun getViewStubLayout(objectsExist: Boolean): Int {
        return if (objectsExist) R.layout.task_list else R.layout.no_tasks
    }

    private fun initViews() {
        vs = findViewById(R.id.view_stub)
    }
}