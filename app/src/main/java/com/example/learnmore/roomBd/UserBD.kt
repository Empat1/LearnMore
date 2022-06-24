package com.example.learnmore.roomBd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserBD(@PrimaryKey(autoGenerate = true) val id: Int,
                  @ColumnInfo(name = "login") val login: String,
                  @ColumnInfo val password : String)
