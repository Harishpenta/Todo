package com.curious.notes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curious.notes.db.Task
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

    fun addNote(task: Task) = viewModelScope.launch {
        mainRepository.addNote(task)
    }

    fun deleteNote(task: Task) = viewModelScope.launch {
        mainRepository.deleteNote(task)
    }

    fun setAsCompletedNote(id: Int?, isCompleted: Boolean) = viewModelScope.launch {
        id?.let { mainRepository.setNoteAsCompleted(it, isCompleted) }
    }
}