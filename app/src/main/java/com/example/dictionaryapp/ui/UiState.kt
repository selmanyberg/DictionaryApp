package com.example.dictionaryapp.ui

data class UiState(
    val wordList: List<WordItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)