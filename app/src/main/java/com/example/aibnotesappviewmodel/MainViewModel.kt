package com.example.aibnotesappviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (application: Application): AndroidViewModel(application) {
    private val repository: NoteRepository
    private val notes: LiveData<List<MyNote>>

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        notes = repository.getNotes
    }

    fun getNotes(): LiveData<List<MyNote>> {
        return notes
    }

    fun addNote(noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addNote(MyNote(0, noteText))
        }
    }

    fun editNote(noteID: Int, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateNote(MyNote(noteID,noteText))
        }
    }

    fun deleteNote(noteID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteNote(MyNote(noteID,""))
        }
    }
}