package com.example.dbsampleapp.repository

import com.example.dbsampleapp.model.ContentEntity

interface ContentRepository {

    suspend fun insert(item: ContentEntity)
}