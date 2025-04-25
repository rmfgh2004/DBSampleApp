package com.example.dbsampleapp.repository

import com.example.dbsampleapp.data.dao.ContentDao
import com.example.dbsampleapp.model.ContentEntity
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentDao: ContentDao) : ContentRepository {

    override suspend fun insert(item: ContentEntity) {
        contentDao.insert(item)
    }
}