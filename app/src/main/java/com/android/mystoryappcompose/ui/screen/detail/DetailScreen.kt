package com.android.mystoryappcompose.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.android.mystoryappcompose.model.PlayerEntity
import com.android.mystoryappcompose.ui.UiState
import com.android.mystoryappcompose.ui.component.ErrorContent
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(playerId: Int, navController: NavController, scaffoldState: ScaffoldState) {
    val detailViewModel = hiltViewModel<DetailViewModel>()
    detailViewModel.player.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> detailViewModel.getPlayer(playerId)
            is UiState.Error -> ErrorContent()
            is UiState.Success -> {
                DetailContent(
                    uiState.data,
                    navController,
                    scaffoldState,
                    detailViewModel::updateFavoritePlayer
                )
            }
        }
    }
}
@Composable
fun DetailContent(
    player: PlayerEntity,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoritePlayer: (id: Int, isFavorite: Boolean) -> Unit,
){
    val coroutineScope = rememberCoroutineScope()
    val (id, name, club, position, description, image, isFavorite) = player

    Box (modifier = Modifier.fillMaxWidth()){

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Box {
                AsyncImage(
                    model = image,
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Box (
                    modifier = Modifier
                        .clip(RoundedCornerShape(topEnd = 8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .align(Alignment.TopEnd)
                ){
                    Row (modifier = Modifier.padding(8.dp)){
                        Text(
                            text = position,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                            )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Icon(
                        imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface,
                        contentDescription = name,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(100))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onUpdateFavoritePlayer(id ?: 0, !isFavorite)
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "$name ${if (isFavorite) "removed from" else "added as"} favorite ",
                                        actionLabel = "Dismiss",
                                        duration = androidx.compose.material.SnackbarDuration.Short
                                    )
                                }
                            },

                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = club, style = MaterialTheme.typography.titleSmall)

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = description, style = MaterialTheme.typography.titleSmall )

            }
        }

        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .size(40.dp)
                .testTag("back_button")
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
            )
        }

    }
}