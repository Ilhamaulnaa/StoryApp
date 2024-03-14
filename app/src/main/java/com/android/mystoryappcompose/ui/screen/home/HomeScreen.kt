package com.android.mystoryappcompose.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.mystoryappcompose.model.PlayerEntity
import com.android.mystoryappcompose.ui.UiState
import com.android.mystoryappcompose.ui.component.AvailableContent
import com.android.mystoryappcompose.ui.component.EmptyContent
import com.android.mystoryappcompose.ui.component.ErrorContent
import com.android.mystoryappcompose.ui.component.LoadingDiag
import com.android.mystoryappcompose.ui.component.SearchBar

@Composable
fun HomeScreen(navController: NavController, scaffoldState: ScaffoldState) {

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val homeState by homeViewModel.homeState

    homeViewModel.allPlayer.collectAsState(UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> LoadingDiag()
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                HomeContent(
                    listPlayer = uiState.data,
                    navController =navController,
                    scaffoldState = scaffoldState,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange,
                    onUpdateFavoritePlayer = homeViewModel::updateFavoritePlayer
                )
            }
        }
    }
}
@Composable
fun HomeContent(
    listPlayer: List<PlayerEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavoritePlayer: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        SearchBar(query = query, onQueryChange = onQueryChange)
        when (listPlayer.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(listPlayer, navController, scaffoldState, onUpdateFavoritePlayer)
        }
    }
}