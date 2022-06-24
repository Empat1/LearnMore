package com.example.learnmore.roomBd

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface UserDao {

    @Query("SELECT * FROM user")
    fun getAlphabetizedWords(): Flow<List<UserBD>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserBD)

    @Query("DELETE FROM user")
    fun deleteAll()
}