package com.example.dictionaryapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.data.DataResponse
import com.example.dictionaryapp.data.WordListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * Responsible for fetching and updating data, for search/filter operations
 * and for sending UI updates reflecting these data changes.
 */
@HiltViewModel
class WordListViewModel @Inject constructor(
    private val repository: WordListRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    private val _userInput = MutableStateFlow("")
    val userInput: StateFlow<String> get() = _userInput

    private val deletionMutex = Mutex()
    private var searchJob: Job? = null
    private var wordList = emptyList<WordItem>()

    init {
        fetchWords()
    }

    fun fetchWords() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            when (val response = repository.fetchWords()) {
                is DataResponse.Success -> {
                    wordList = response.wordList
                    _uiState.value = _uiState.value.copy(
                        wordList = response.wordList,
                        isLoading = false
                    )
                }

                is DataResponse.Error -> _uiState.value =
                    _uiState.value.copy(
                        errorMessage = response.errorMessage,
                        isLoading = false
                    )

                is DataResponse.Loading -> _uiState.value =
                    _uiState.value.copy(
                        isLoading = true
                    )
            }
        }
    }

    fun deleteWord(word: WordItem) {
        viewModelScope.launch {
            // Avoid race conditions
            deletionMutex.withLock {
                // Remove from word list - not persistent if app restarts
                val currentWordList = wordList.toMutableList()
                currentWordList.removeAll { it.word == word.word }
                wordList = currentWordList

                // Remove from currently displayed list
                val currentDisplayedList = _uiState.value.copy().wordList.toMutableList()
                currentDisplayedList.removeAll { it.word == word.word }
                _uiState.value = uiState.value.copy(
                    wordList = currentDisplayedList,
                )
            }
        }
    }

    fun onSearchQueryChanged(searchQuery: String) {
        _userInput.value = searchQuery

        searchJob?.cancel()
        // Search in background thread and avoid race conditions
        searchJob = viewModelScope.launch {
            deletionMutex.withLock {
                delay(300) // Debounce the search for a short delay for better UX
                val results = performSearchAndFilter(searchQuery)
                _uiState.value = uiState.value.copy(
                    wordList = results,
                )
            }
        }
    }

    private fun performSearchAndFilter(searchQuery: String): List<WordItem> {
        if (searchQuery == "") {
            return wordList
        }
        val filteredWords = wordList.filter {
            it.word.contains(searchQuery, ignoreCase = true)
        }
        return filteredWords
    }
}
