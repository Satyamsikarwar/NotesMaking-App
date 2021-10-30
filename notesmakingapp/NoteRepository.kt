package com.example.notesmakingapp

class NoteRepository(private val noteDao: NoteDao) {
    val allNote=noteDao.getAllNotes()
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }
    suspend fun delete(note:Note) {
        noteDao.delete(note)
    }
}