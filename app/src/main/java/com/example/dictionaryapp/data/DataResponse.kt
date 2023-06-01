package com.example.dictionaryapp.data

import com.example.dictionaryapp.ui.WordItem

sealed class DataResponse {
    object Loading : DataResponse()
    data class Success(val wordList: List<WordItem>) : DataResponse()
    data class Error(val errorMessage: String) : DataResponse()
}
