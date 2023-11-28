package com.pavan.notes.Viewmodal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pavan.notes.Entities.notes
import com.pavan.notes.Repository.NoteRepository
import com.pavan.notes.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewmodel(application: Application):AndroidViewModel(application) {


       private val  repository = NoteRepository(NoteDatabase.getdatabase(application).getnotedao())
        val allnotes = repository.allnotes


    fun deletenote(note:notes){
        viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    }
    fun insertnote(note: notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }
    fun updatenote(note: notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(note)
        }
    }
}