package com.example.dictionaryapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dictionaryapp.ui.WordItem
import com.example.dictionaryapp.ui.WordListViewModel
import com.example.dictionaryapp.ui.component.DetailsComponent
import com.example.dictionaryapp.ui.component.HomeScreen

@Composable
fun DictionaryApp(wordListViewModel: WordListViewModel) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(wordListViewModel, navController)
        }
        composable(
            "wordDetails/{wordId}",
            arguments = listOf(navArgument("wordId") { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val word = arguments.getString("wordId") ?: ""
            DetailsComponent(WordItem(word), navController, wordListViewModel)
        }
    }
}
