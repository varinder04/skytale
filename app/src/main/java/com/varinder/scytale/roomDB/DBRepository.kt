package com.varinder.scytale.roomDB

import androidx.lifecycle.LiveData
import com.varinder.scytale.roomDB.entities.MessageEntity
import javax.inject.Inject

class DBRepository @Inject constructor(val appDatabase: AppDatabase) {

    suspend fun insert(message: MessageEntity): Long {
        return appDatabase.dao()
            .insert(message)
    }

    suspend fun delete(message: MessageEntity) {
        appDatabase.dao().delete(message)
    }

    suspend fun deleteAll() {
        appDatabase.dao().deleteAll()
    }

    fun getAllMessages(): LiveData<List<MessageEntity>> {
        return appDatabase.dao().getAllMessages()
    }


}