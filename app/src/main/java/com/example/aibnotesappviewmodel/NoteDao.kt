package com.example.aibnotesappviewmodel

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: MyNote)

    @Query("SELECT * FROM NotesTable ORDER BY id ASC")
    fun getNotes(): LiveData<List<MyNote>>

    @Update
    suspend fun updateNote(note: MyNote)

    @Delete
    suspend fun deleteNote(note: MyNote)





}