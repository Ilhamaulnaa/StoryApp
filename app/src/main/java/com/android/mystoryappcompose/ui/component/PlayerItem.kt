package com.android.mystoryappcompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.android.mystoryappcompose.model.PlayerEntity
import com.android.mystoryappcompose.ui.navigation.Screen
import com.android.mystoryappcompose.ui.theme.MyStoryAppComposeTheme
import kotlinx.coroutines.launch

@Composable
fun AvailableContent(
    listPlayer: List<PlayerEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoritePlayer: (id: Int, isFavorite: Boolean) -> Unit,
) {
    LazyColumn {
        items(listPlayer, key = { it.name }) { player ->
            PlayerItem(player, navController, scaffoldState, onUpdateFavoritePlayer)
        }
    }
}

@Composable
fun PlayerItem(
    player: PlayerEntity,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoritePlayer: (id: Int, isFavorite: Boolean) -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()
    val (id, name,club, position, _, image, isFavorite) = player

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .border(1.dp, Color.LightGray.copy(0.5f), MaterialTheme.shapes.small)
            .clickable { navController.navigate(Screen.Detail.createRoute(id ?: 0)) }
    ) {
        Column {
            Box {
                AsyncImage(
                    model = image,
                    contentDescription = name,
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(image),
                    modifier = Modifier.fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(bottomEnd = 8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = position,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                    }
                }
            }

            Column (modifier = Modifier.padding(16.dp)){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
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
                            }
                    )
                }
                Text(
                    text = club,
                    style = MaterialTheme.typography.titleSmall
                )

            }

        }

    }
}

@Preview
@Composable
fun PlayerItemPreview() {
    MyStoryAppComposeTheme {

    }
    
}

