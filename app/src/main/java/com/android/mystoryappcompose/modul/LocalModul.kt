package com.android.mystoryappcompose.modul

import android.app.Application
import androidx.room.Room
import com.android.mystoryappcompose.local.PlayerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, PlayerDatabase::class.java, "player.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: PlayerDatabase) = database.playerDao()
}

