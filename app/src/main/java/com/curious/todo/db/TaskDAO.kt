package com.curious.todo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(task: Task)

    @Delete
    suspend fun deleteNote(task: Task)

    @Query("UPDATE task_table SET isCompleted = :isCompletedNote WHERE id = :id")
    suspend fun setNoteAsCompleted(id: Int, isCompletedNote: Boolean)

    @Query("SELECT * FROM task_table ORDER BY createdDate DESC")
    fun getAllNotes(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE type ='B'")
    fun getAllBusinessNotes(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE type ='P'")
    fun getAllPersonalNotes(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE isCompleted = :isCompleted and type ='B' ")
    fun getAllBusinessCompletedNotes(isCompleted: Boolean = true): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE isCompleted = :isCompleted and type ='P'")
    fun getAllPersonalCompletedNotes(isCompleted: Boolean = true): LiveData<List<Task>>
}