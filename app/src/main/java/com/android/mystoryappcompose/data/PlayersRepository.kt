package com.android.mystoryappcompose.data

import com.android.mystoryappcompose.local.PlayerDao
import com.android.mystoryappcompose.model.PlayerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayersRepository @Inject constructor(private val playerDao: PlayerDao) {
    fun getAllPlayer() = playerDao.getAllPlayer()
    fun getAllFavoritePlayer() = playerDao.getAllFavoritePlayer()
    fun getPlayer(id: Int) = playerDao.getPlayer(id)
    fun searchPlayer(query: String) = playerDao.searchPlayer(query)
    suspend fun insertAllPlayer(player: List<PlayerEntity>) = playerDao.insertAllPlayer(player)
    suspend fun updateFavoritePlayer(id: Int, isFavorite: Boolean) = playerDao.updateFavoritePlayer(id, isFavorite)
}