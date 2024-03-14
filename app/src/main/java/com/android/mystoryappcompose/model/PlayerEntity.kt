package com.android.mystoryappcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val club: String,
    val position: String,
    val description: String,
    val image: Int,
    var isFavorite: Boolean = false,
)