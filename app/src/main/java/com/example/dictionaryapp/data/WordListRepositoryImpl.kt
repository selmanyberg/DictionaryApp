package com.example.dictionaryapp.data

import com.example.dictionaryapp.ui.WordItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class WordListRepositoryImpl : WordListRepository {

    override suspend fun fetchWords(): DataResponse = withContext(Dispatchers.IO) {

        val wordList: MutableList<WordItem> = mutableListOf()
        val url = "https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt"

        try {
            val urlObject = URL(url)
            val connection = urlObject.openConnection()
            val inputStream = connection.getInputStream()
            val reader = inputStream.bufferedReader()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let { wordList.add(WordItem(it)) }
            }

            reader.close()
            inputStream.close()

            DataResponse.Success(wordList)
        } catch (e: Exception) {
            if (e is java.net.ConnectException ||
                e is java.net.SocketTimeoutException ||
                e is java.net.UnknownHostException) {
                DataResponse.Error("Network error")
            } else {
                DataResponse.Error("Error: " + e.message)
            }
        }
    }
}