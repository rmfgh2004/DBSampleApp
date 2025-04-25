package com.example.dbsampleapp.repository

import com.example.dbsampleapp.data.dao.ContentDao
import com.example.dbsampleapp.model.ContentEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentDao: ContentDao) : ContentRepository {

    override fun loadList() = contentDao.selectAll()

    override suspend fun insert(item: ContentEntity) {
        contentDao.insert(item)
    }

    override suspend fun modify(item: ContentEntity) {
        contentDao.insert(item)
    }

    override suspend fun delete(item: ContentEntity) {
        contentDao.delete(item)
    }
}