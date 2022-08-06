package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.myapplication.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ContentMainBinding

    var notePosition= POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterCourses = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?: // if savedInstance is null (initial creation) get the position from intent
            intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET) // return POSITION_NOT_SET if there is no value
        if(notePosition!= POSITION_NOT_SET){
            displayNote()
        }
        else{
             DataManager.notes.add(NoteInfo())  // construct a new empty note and add it to our DataManager
            notePosition = DataManager.notes.lastIndex // update note position to our new created empty note
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition] // notePosition is like the index
        binding.textNoteTitle.setText(note.title)
        binding.textNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.spinnerCourses.setSelection(coursePosition)
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if(notePosition >= DataManager.notes.lastIndex){
            val menuItem = menu.findItem(R.id.action_next)
                menuItem.icon=getDrawable(R.drawable.ic_block_white_24)
                menuItem.isEnabled = false
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION,notePosition)
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title =  binding.textNoteTitle.text.toString()
        note.text = binding.textNoteText.text.toString()
        note.course = binding.spinnerCourses.selectedItem as CourseInfo
    }
}