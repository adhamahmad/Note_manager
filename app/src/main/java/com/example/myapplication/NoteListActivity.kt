package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ContentNoteListBinding

class NoteListActivity : AppCompatActivity() {
    private  lateinit var binding: ContentNoteListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newNoteButton.setOnClickListener{view->
               val activityIntent = Intent(this,MainActivity::class.java)
               startActivity(activityIntent)
        }

        binding.listNotes.adapter= ArrayAdapter(this,
                          android.R.layout.simple_list_item_1,
                          DataManager.notes)

        binding.listNotes.setOnItemClickListener{parent,view,position,id->
                val activityIntent = Intent(this,MainActivity::class.java)
                activityIntent.putExtra(NOTE_POSITION,position)
                startActivity(activityIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        (binding.listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}