package com.curious.notes.repositories

import com.curious.notes.db.Note
import com.curious.notes.db.NoteDAO
import javax.inject.Inject

class MainRepository @Inject constructor(private val noteDAO: NoteDAO) {
    suspend fun addNote(note: Note) = noteDAO.addNote(note)

    suspend fun deleteNote(note: Note) = noteDAO.deleteNote(note)

    suspend fun setNoteAsCompleted(id : Int, isCompleted :Boolean) = noteDAO.setNoteAsCompleted(id,isCompleted)

    fun getAllNotes() = noteDAO.getAllNotes()

    fun getAllBusinessNotes() = noteDAO.getAllBusinessNotes()

    fun getAllPersonalNotes() = noteDAO.getAllPersonalNotes()

    fun getAllBusinessCompletedNotes() = noteDAO.getAllBusinessCompletedNotes()

    fun getAllPersonalCompletedNotes() = noteDAO.getAllPersonalCompletedNotes()
}