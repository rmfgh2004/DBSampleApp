package com.example.dbsampleapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dbsampleapp.data.dao.ContentDao
import com.example.dbsampleapp.model.ContentEntity

@Database(entities = [ContentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao() : ContentDao
}