package com.example.dictionaryapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dictionaryapp.ui.WordListViewModel
import com.example.dictionaryapp.util.DictionaryAppTheme
import com.example.dictionaryapp.util.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(wordListViewModel: WordListViewModel, navController: NavHostController) {
    DictionaryAppTheme {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Dictionary", fontSize = 24.sp, color = Color.White
                    )
                }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Purple40
                )
            )
        }) { paddingValues ->

            HomeContent(
                wordListViewModel = wordListViewModel,
                navController = navController,
                paddingValues = paddingValues,
            )
        }
    }
}

@Composable
fun HomeContent(
    wordListViewModel: WordListViewModel,
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val uiState by wordListViewModel.uiState.collectAsState()

    // Function to handle retry action on error
    val retryAction = {
        wordListViewModel.fetchWords()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        SearchInputComponent(wordListViewModel)
        if (uiState.isLoading) {
            LoadingComponent()
        } else if (uiState.errorMessage == null) {
            if (uiState.wordList.isNotEmpty()) {
                // Show word list
                WordListComponent(uiState.wordList, navController)
            } else {
                // Show empty state
                EmptyStateComponent()
            }
        } else {
            // Show error message
            ErrorComponent(onRetry = retryAction, message = uiState.errorMessage ?: "Unknown error")
        }
    }
}
