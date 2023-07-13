package com.varinder.scytale.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import androidx.room.Update
import com.varinder.scytale.roomDB.entities.MessageEntity

@Dao
interface Dao {
    // below method is use to
    // add data to database.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntity: MessageEntity): Long

    // below method is use to update
    @Update
    fun update(model: MessageEntity?)

    // below line is use to delete
    @Delete
    fun delete(model: MessageEntity?)

    // on below line we are making query to
    // delete all messages from our database.
    @Query("DELETE FROM Messages")
    fun deleteAll()

    // below line is to read all the messages from our database.
    @Query("SELECT * FROM Messages")
    fun getAllMessages(): LiveData<List<MessageEntity>>
}