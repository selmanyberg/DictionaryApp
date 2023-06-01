package com.example.dictionaryapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dictionaryapp.ui.WordListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInputComponent(
    wordListViewModel: WordListViewModel
) {
    val currentQuery by wordListViewModel.userInput.collectAsState()

    TextField(
        value = currentQuery,
        onValueChange = { query ->
            wordListViewModel.onSearchQueryChanged(query)
        },
        label = { Text(text = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
    )
}