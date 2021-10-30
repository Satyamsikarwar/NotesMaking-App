package com.example.notesmakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit private var viewModel: NoteViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = NoteRVAdapter(this, this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewmodel::class.java)
        viewModel.allNotes.observe(this, { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(note: Note) {
      viewModel.deleteNote(note)
    }

    fun buttonClick(view: View) {
        val noteText= EditText.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
        }
    }
}