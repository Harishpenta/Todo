package com.curious.notes.repositories

import com.curious.notes.db.Task
import com.curious.notes.db.TaskDAO
import javax.inject.Inject

class MainRepository @Inject constructor(private val taskDAO: TaskDAO) {
    suspend fun addNote(task: Task) = taskDAO.addNote(task)

    suspend fun deleteNote(task: Task) = taskDAO.deleteNote(task)

    suspend fun setNoteAsCompleted(id : Int, isCompleted :Boolean) = taskDAO.setNoteAsCompleted(id,isCompleted)

    fun getAllNotes() = taskDAO.getAllNotes()

    fun getAllBusinessNotes() = taskDAO.getAllBusinessNotes()

    fun getAllPersonalNotes() = taskDAO.getAllPersonalNotes()

    fun getAllBusinessCompletedNotes() = taskDAO.getAllBusinessCompletedNotes()

    fun getAllPersonalCompletedNotes() = taskDAO.getAllPersonalCompletedNotes()
}