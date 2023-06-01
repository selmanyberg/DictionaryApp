package com.example.dictionaryapp.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dictionaryapp.ui.WordItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordListComponent(
    wordList: List<WordItem>,
    navController: NavController,
) {
    LazyColumn {
        val groupedWords = wordList.groupBy { it.word.first().toUpperCase() }
        groupedWords.keys.sorted().forEach { key ->
            stickyHeader {
                Text(
                    text = key.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            items(groupedWords[key] ?: emptyList()) { word ->
                WordItem(word, navController)
            }
        }
    }
}

@Composable
fun WordItem(wordItem: WordItem, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = wordItem.word,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    navController.navigate("wordDetails/${wordItem.word}") {
                        popUpTo("wordList") {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
        )
    }
}