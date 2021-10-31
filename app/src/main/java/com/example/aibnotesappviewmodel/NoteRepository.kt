package com.example.aibnotesappviewmodel

import androidx.lifecycle.LiveData

class NoteRepository (private val noteDao: NoteDao) {

  //  val getNotes: List<MyNote> = noteDao.getNotes()
    val getNotes: LiveData<List<MyNote>> = noteDao.getNotes()

    suspend fun addNote(note: MyNote){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: MyNote){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: MyNote){
        noteDao.deleteNote(note)
    }
}