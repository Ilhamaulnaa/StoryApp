package com.android.mystoryappcompose.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.mystoryappcompose.model.PlayerEntity


@Database(entities = [PlayerEntity::class], version = 1, exportSchema = false)
abstract class PlayerDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}