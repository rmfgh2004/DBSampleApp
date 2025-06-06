package com.example.dbsampleapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Content")
data class ContentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    var content: String,

    @ColumnInfo
    var memo : String? = null,

    @ColumnInfo
    var isDone : Boolean = false
) : Serializable
