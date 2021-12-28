package com.curious.notes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var description: String,
    var createdDate: Long,
    var type: String,
    var isCompleted: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}