package com.android.mystoryappcompose.modul

import com.android.mystoryappcompose.data.PlayersRepository
import com.android.mystoryappcompose.local.PlayerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(playerDao: PlayerDao) = PlayersRepository(playerDao)
}

