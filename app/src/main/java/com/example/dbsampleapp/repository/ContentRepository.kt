package com.example.dbsampleapp.repository

import com.example.dbsampleapp.model.ContentEntity
import kotlinx.coroutines.flow.Flow

interface ContentRepository {

    fun loadList() : Flow<List<ContentEntity>>

    suspend fun insert(item: ContentEntity)

    suspend fun modify(item: ContentEntity)

    suspend fun delete(item: ContentEntity)
}