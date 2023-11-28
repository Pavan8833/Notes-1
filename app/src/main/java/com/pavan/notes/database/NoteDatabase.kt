package com.pavan.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pavan.notes.Dao.notedao
import com.pavan.notes.Entities.notes

@Database(entities = [notes::class],version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getnotedao():notedao



    companion object{

        private var INSTANCE : NoteDatabase?= null
        fun getdatabase(context: Context) : NoteDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "notes_database")
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }

}