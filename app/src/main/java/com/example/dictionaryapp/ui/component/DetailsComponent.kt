package com.example.dictionaryapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dictionaryapp.ui.WordItem
import com.example.dictionaryapp.ui.WordListViewModel
import com.example.dictionaryapp.util.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsComponent(
    wordItem: WordItem,
    navController: NavController,
    wordListViewModel: WordListViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Details",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Purple40
                )
            )
        },
        content = {
            LazyColumn {
                item {
                    Text(
                        text = wordItem.word,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        },
        bottomBar = {
            BottomAppBar {
                Button(
                    onClick = {
                        wordListViewModel.deleteWord(wordItem)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Remove",
                        fontSize = 20.sp,
                    )
                }
            }
        }
    )
}