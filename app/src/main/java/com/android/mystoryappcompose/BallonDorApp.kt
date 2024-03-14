import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.mystoryappcompose.ui.navigation.NavigationItem
import com.android.mystoryappcompose.ui.navigation.Screen
import com.android.mystoryappcompose.ui.screen.about.ProfileScreen
import com.android.mystoryappcompose.ui.screen.detail.DetailScreen
import com.android.mystoryappcompose.ui.screen.favorite.FavoriteScreen
import com.android.mystoryappcompose.ui.screen.home.HomeScreen
import com.android.mystoryappcompose.ui.theme.MyStoryAppComposeTheme


@Composable
fun BallonDorApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    androidx.compose.material.Scaffold (
        bottomBar = {
            if(currentRoute != Screen.Detail.route){
                BottomBar(navController, currentRoute)
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            androidx.compose.material.SnackbarHost(hostState = it) { data ->
                androidx.compose.material.Snackbar(snackbarData = data, shape = RoundedCornerShape(8.dp))
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController, scaffoldState)
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("playerId") { type = NavType.IntType }
                )
            ) {
                val playerId = it.arguments?.getInt("playerId") ?: 0
                DetailScreen(playerId, navController, scaffoldState)
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(navController, scaffoldState)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(onBackClick = {navController.navigateUp()})
            }
        }
    }
}
@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?,
) {
    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Rounded.Home,
            screen = Screen.Home
        ),
        NavigationItem(
            title = "Favorite",
            icon = Icons.Rounded.Favorite,
            screen = Screen.Favorite
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Rounded.Person,
            screen = Screen.Profile
        ),
    )

    BottomNavigation(backgroundColor = Color.White) {
        navigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Preview
@Composable
fun BallonDorAppPreview() {
    MyStoryAppComposeTheme {
        BallonDorApp()
    }
}