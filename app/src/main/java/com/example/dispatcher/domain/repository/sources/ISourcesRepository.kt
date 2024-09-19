package com.example.dispatcher.domain.repository.sources

interface ISourcesRepository {
    suspend fun getSourceNames(): List<String>
}