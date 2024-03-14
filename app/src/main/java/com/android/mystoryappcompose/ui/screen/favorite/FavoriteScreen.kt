package com.android.mystoryappcompose.ui.screen.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.mystoryappcompose.model.PlayerEntity
import com.android.mystoryappcompose.ui.UiState
import com.android.mystoryappcompose.ui.component.AvailableContent
import com.android.mystoryappcompose.ui.component.EmptyContent
import com.android.mystoryappcompose.ui.component.ErrorContent
import com.android.mystoryappcompose.ui.component.LoadingDiag

@Composable
fun FavoriteScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    favoriteViewModel.allFavoritePlayer.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> LoadingDiag()
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                FavoriteContent(
                    listFavoritePlayer = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoritePlayer = favoriteViewModel::updateFavoritePlayer
                )
            }
        }
    }
}
@Composable
fun FavoriteContent(
    listFavoritePlayer: List<PlayerEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoritePlayer: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavoritePlayer.isEmpty()) {
        true -> EmptyContent()
        false -> AvailableContent(listFavoritePlayer, navController, scaffoldState, onUpdateFavoritePlayer)
    }
}