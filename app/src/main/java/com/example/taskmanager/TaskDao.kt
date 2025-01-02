package com.example.taskmanager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAllTasks(): List<Task>?

    @Query("SELECT * FROM task WHERE id = :taskID")
    fun getTaskById(taskID: Int): Task?

    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)


}