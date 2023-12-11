package com.example.room_presistence

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room_presistence.constant.Constant
import com.example.room_presistence.databinding.ActivityMainBinding
import com.example.room_presistence.room.NoteDB

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var noteAdapter: NoteAdapter

    val db by lazy { NoteDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    private fun setupListener() {
        binding.btnCreate.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(id: Int, type: Int) {
        startActivity(Intent(applicationContext, EditActivity::class.java)
            .putExtra("intent_id", id)
            .putExtra("intent_type", type))
    }
}