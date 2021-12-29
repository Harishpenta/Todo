package com.curious.todo.di

import android.content.Context
import androidx.room.Room
import com.curious.todo.db.TaskDatabase
import com.curious.todo.utils.Constants.NOTE_DATABASE_NAME
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
        Room.databaseBuilder(app, TaskDatabase::class.java, NOTE_DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideNoteDAO(db: TaskDatabase) = db.getNoteDAO()
}