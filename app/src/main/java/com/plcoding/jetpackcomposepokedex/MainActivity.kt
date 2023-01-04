package com.plcoding.jetpackcomposepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.plcoding.jetpackcomposepokedex.pokemonlist.PokemonListScreen
import com.plcoding.jetpackcomposepokedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = POKEMON_LIST_SCREEN
                ) {
                    composable(POKEMON_LIST_SCREEN) {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        POKEMON_DETAIL_SCREEN,
                        arguments = listOf(
                            navArgument(PARAM_DOMINANT_COLOR) {
                                type = NavType.IntType
                            },
                            navArgument(PARAM_POKEMON_NAME) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt(PARAM_DOMINANT_COLOR)
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString(PARAM_POKEMON_NAME)
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val POKEMON_LIST_SCREEN = "pokemon_list_screen"

        const val PARAM_DOMINANT_COLOR = "dominantColor"
        const val PARAM_POKEMON_NAME = "pokemonName"
        const val POKEMON_DETAIL_SCREEN = "pokemon_detail_screen/{$PARAM_DOMINANT_COLOR}/{$PARAM_POKEMON_NAME}"

    }
}
