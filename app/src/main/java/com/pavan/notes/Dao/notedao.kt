package com.pavan.notes.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pavan.notes.Entities.notes

@Dao
interface notedao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:notes)

    @Update
    suspend fun update(note: notes)

    @Delete
    suspend fun delete(note: notes)

    @Query("select *  from notes order by id ASC")
    fun getallnotes():LiveData<List<notes>>

}