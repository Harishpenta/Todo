package com.curious.notes.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("UPDATE note_table SET isCompleted = :isCompletedNote WHERE id = :id")
    suspend fun setNoteAsCompleted(id: Int, isCompletedNote: Boolean)

    @Query("SELECT * FROM note_table ORDER BY createdDate DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE type ='B'")
    fun getAllBusinessNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE type ='P'")
    fun getAllPersonalNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE isCompleted = :isCompleted and type ='B' ")
    fun getAllBusinessCompletedNotes(isCompleted: Boolean = true): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE isCompleted = :isCompleted and type ='P'")
    fun getAllPersonalCompletedNotes(isCompleted: Boolean = true): LiveData<List<Note>>
}