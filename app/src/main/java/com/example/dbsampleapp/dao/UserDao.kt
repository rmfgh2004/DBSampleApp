package com.example.dbsampleapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dbsampleapp.entity.User

@Dao
interface UserDao {

    @Query("SELECT * from user WHERE id LIKE :id")
    fun findById(id: String): User?

    @Insert
    fun insert(user: User)
}