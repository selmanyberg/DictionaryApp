package com.example.dictionaryapp.data

interface WordListRepository {
    suspend fun fetchWords(): DataResponse
}