package com.android.mystoryappcompose.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.mystoryappcompose.model.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAllPlayer(): Flow<List<PlayerEntity>>

    @Query("SELECT * FROM player WHERE isFavorite = 1")
    fun getAllFavoritePlayer(): Flow<List<PlayerEntity>>

    @Query("SELECT * FROM player WHERE id = :id")
    fun getPlayer(id: Int): Flow<PlayerEntity>

    @Query("SELECT * FROM player WHERE name LIKE '%' || :query || '%'")
    fun searchPlayer(query: String): Flow<List<PlayerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPlayer(playerList: List<PlayerEntity>)

    @Query("UPDATE player SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavoritePlayer(id: Int, isFavorite: Boolean)

}