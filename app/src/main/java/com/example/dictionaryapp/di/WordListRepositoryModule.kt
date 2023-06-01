package com.example.dictionaryapp.di

import com.example.dictionaryapp.data.WordListRepository
import com.example.dictionaryapp.data.WordListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordListRepositoryModule {

    @Provides
    @Singleton
    fun provideWordRepository(
    ): WordListRepository {
        return WordListRepositoryImpl()
    }
}