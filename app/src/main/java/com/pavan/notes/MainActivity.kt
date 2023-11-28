package com.pavan.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pavan.notes.Entities.notes
import com.pavan.notes.Viewmodal.NoteViewmodel

class MainActivity : AppCompatActivity(), Notedeleteclickinterface, Noteclickinterface {

    lateinit var recylerview:RecyclerView
    lateinit var fabbtn:FloatingActionButton
    lateinit var viewmodel: NoteViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recylerview = findViewById(R.id.recyclerview)
        fabbtn = findViewById(R.id.fabbtn)
        recylerview.layoutManager = LinearLayoutManager(this)

        val noteadapter = Noteadapter(this,this,this)
        recylerview.adapter = noteadapter
        viewmodel = ViewModelProvider(this)[NoteViewmodel::class.java]

        viewmodel.allnotes.observe(this) { list ->
            list?.let {
                noteadapter.updateList(it)
            }
        }

        fabbtn.setOnClickListener {
            val intent = Intent(this@MainActivity,Editnoteactivity::class.java)
            startActivity(intent)
            this.finish()
        }



    }

    override fun onDeleteiconitem(note: notes) {
        viewmodel.deletenote(note)
        Toast.makeText(this,"${note.title} deleted",Toast.LENGTH_SHORT).show()
    }

    override fun onnoteclick(note: notes) {
        val intent = Intent(this@MainActivity,Editnoteactivity::class.java)
        intent.putExtra("notetype","edit")
        intent.putExtra("notetitle",note.title)
        intent.putExtra("notedescription",note.description)
        intent.putExtra("noteid",note.id)
        startActivity(intent)
        this.finish()
    }
}