package com.curious.notes.di

import android.content.Context
import androidx.room.Room
import com.curious.notes.db.NoteDatabase
import com.curious.notes.utils.Constants.NOTE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, NoteDatabase::class.java, NOTE_DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideNoteDAO(db: NoteDatabase) = db.getNoteDAO()
}