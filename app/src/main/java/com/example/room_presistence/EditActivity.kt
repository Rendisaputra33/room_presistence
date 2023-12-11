package com.example.room_presistence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.room_presistence.constant.Constant
import com.example.room_presistence.databinding.ActivityEditBinding
import com.example.room_presistence.room.Note
import com.example.room_presistence.room.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { NoteDB(this) }
    private  var noteId: Int = 0

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupListerner()
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType) {
            Constant.TYPE_CREATE -> {
                binding.buttonSave.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                binding.buttonSave.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
                getNote()
            }
            Constant.TYPE_UPDATE -> {
                binding.buttonUpdate.visibility = View.GONE
                getNote()
            }
        }
    }

    private fun setupListerner() {
        binding.buttonSave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteRepository().addNote(
                    Note(0, binding.editTitle.text.toString(),
                        binding.editNote.text.toString()
                    )
                )
                finish()
            }
        }

        binding.buttonUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteRepository().updateNote(
                    Note(0, binding.editTitle.text.toString(),binding.editNote.text.toString())
                )
            }
        }
    }

    fun getNote() {
        noteId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteRepository().getNote(noteId)
            binding.editTitle.setText(notes.title)
            binding.editNote.setText(notes.node)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}