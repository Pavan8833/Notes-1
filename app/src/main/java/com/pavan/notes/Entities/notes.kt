package com.pavan.notes.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class notes (

    val title: String,
    @ColumnInfo("notedescription")val description:String,
    val date:String
        ){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}