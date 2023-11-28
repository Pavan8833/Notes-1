package com.pavan.notes.Repository

import androidx.lifecycle.LiveData
import com.pavan.notes.Dao.notedao
import com.pavan.notes.Entities.notes

class NoteRepository(private val notedao: notedao) {

    val  allnotes : LiveData<List<notes>> = notedao.getallnotes()

    suspend fun insert(note:notes){
        notedao.insert(note)
    }
    suspend fun update(note: notes){
        notedao.update(note)
    }
    suspend fun delete(note: notes){
        notedao.delete(note)
    }


}