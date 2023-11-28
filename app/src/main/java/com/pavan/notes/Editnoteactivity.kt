package com.pavan.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pavan.notes.Entities.notes
import com.pavan.notes.Viewmodal.NoteViewmodel
import java.text.SimpleDateFormat
import java.util.Date

class Editnoteactivity : AppCompatActivity() {

    lateinit var back : ImageView
    lateinit var tickbtn : ImageView
    lateinit var edittitle : EditText
    lateinit var editdescription : EditText
    lateinit var ViewModel: NoteViewmodel
    var id:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editnoteactivity)

        back =  findViewById(R.id.back)
        tickbtn = findViewById(R.id.tick)
        edittitle = findViewById(R.id.texttitle)
        editdescription = findViewById(R.id.note)
        ViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application))
            .get(NoteViewmodel::class.java)
        val notetype = intent.getStringExtra("notetype")
        if(notetype.equals("edit")){
            val title = intent.getStringExtra("notetitle")
            val description = intent.getStringExtra("notedescription")
            id = intent.getIntExtra("noteid",-1)
            edittitle.setText(title)
            editdescription.setText(description)
        }

        tickbtn.setOnClickListener {
            val title = edittitle.text.toString()
            val description = editdescription.text.toString()

            if(notetype.equals("edit")){
                if(title.isNotEmpty() && description.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd mm, yyyy - HH:mm")
                    val current:String = sdf.format(Date())
                    val updatenote = notes(title,description,current)
                       updatenote.id = id
                    ViewModel.updatenote(updatenote)
                    Toast.makeText(this,"note is updated",Toast.LENGTH_SHORT).show()
                }
            }else{
                if(title.isNotEmpty() && description.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd mm, yyyy - HH:mm")
                    val current:String = sdf.format(Date())
                    ViewModel.insertnote(notes(title,description,current))
                    Toast.makeText(this,"note is created",Toast.LENGTH_SHORT).show()
                }
            }

        }
        startActivity(Intent(applicationContext,MainActivity::class.java))
        this.finish()

    }
}