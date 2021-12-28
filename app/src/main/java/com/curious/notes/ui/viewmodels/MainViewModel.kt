package com.curious.notes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curious.notes.db.Note
import com.curious.notes.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val allNotes = mainRepository.getAllNotes()
    val allBusinessTypeNotes = mainRepository.getAllBusinessNotes()
    val allPersonalTypeNotes = mainRepository.getAllPersonalNotes()
    val allBusinessCompletedList = mainRepository.getAllBusinessCompletedNotes()
    val allPersonalCompletedList = mainRepository.getAllPersonalCompletedNotes()

//    val allPersonalCompletedList =
//        fun(isCompleted: Boolean) = mainRepository.getAllPersonalCompletedNotes(isCompleted)

    fun addNote(note: Note) = viewModelScope.launch {
        mainRepository.addNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        mainRepository.deleteNote(note)
    }

    fun setAsCompletedNote(id: Int?, isCompleted: Boolean) = viewModelScope.launch {
        id?.let { mainRepository.setNoteAsCompleted(it, isCompleted) }
    }
}