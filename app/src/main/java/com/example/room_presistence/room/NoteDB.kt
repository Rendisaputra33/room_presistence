package com.example.room_presistence.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB: RoomDatabase() {
    abstract fun noteRepository(): NoteRepository

    companion object {
        private val LOCK = Any()

        @Volatile
        private var instance: NoteDB? = null

        operator fun invoke(ctx: Context) = instance ?: synchronized(LOCK) {
            instance ?: init(ctx).also {
                instance = it
            }
        }

        private fun init(ctx: Context) = Room.databaseBuilder(
            ctx.applicationContext,
            NoteDB::class.java,
            "note-db.db"
        ).build()
    }
}