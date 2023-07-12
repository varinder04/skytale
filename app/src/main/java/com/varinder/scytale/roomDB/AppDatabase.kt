package com.varinder.scytale.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.varinder.scytale.roomDB.entities.MessageEntity

@Database(
    version = 1,
    entities = [MessageEntity::class],
)
abstract class AppDatabase :RoomDatabase(){

    abstract fun dao(): Dao
}