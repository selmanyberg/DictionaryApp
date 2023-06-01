package com.example.dictionaryapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingComponent() {
    Column {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        Text(text = "Loading...")
    }
}