package com.example.taskmanager

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun getAllTasks(): List<Task> = taskDao.getAllTasks() ?: emptyList()

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun update(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun delete(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun getTaskById(taskID: Int): Task? {
        return taskDao.getTaskById(taskID)
    }
}