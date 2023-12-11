package com.example.room_presistence.room

import androidx.room.*

@Dao
interface NoteRepository {
    @Insert
    suspend fun addNote(node: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getNote(): List<Note>

    @Query("SELECT * FROM note WHERE id=:note_id LIMIT 1")
    suspend fun getNote(note_id: Int): Note
}