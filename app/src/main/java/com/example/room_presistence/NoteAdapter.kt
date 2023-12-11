package com.example.room_presistence

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.room_presistence.databinding.ActivityMainBinding
import com.example.room_presistence.databinding.AdapterMainBinding
import com.example.room_presistence.room.Note

class NoteAdapter(
    private var notes: ArrayList<Note>,
    private val listener: OnAdapterListener
    ): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

        private lateinit var binding: AdapterMainBinding

        inner class NoteViewHolder(val binding: AdapterMainBinding): RecyclerView.ViewHolder(binding.root)

        interface OnAdapterListener {
            fun onRead(note: Note)
            fun onUpdate(note: Note)
            fun onDelete(note: Note)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
            binding = AdapterMainBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return NoteViewHolder(binding)
        }

        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        with(holder) {
            with(notes[position]) {
                binding.textTitle.text = notes[position].title
                binding.textTitle.setOnClickListener {
                    listener.onRead(notes[position])
                }

                binding.iconEdit.setOnClickListener {
                    listener.onUpdate(notes[position])
                }

                binding.iconDelete.setOnClickListener {
                    listener.onDelete(notes[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = notes.size

    fun setData(newList: ArrayList<Note>) {
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }
}