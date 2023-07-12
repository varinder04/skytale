package com.varinder.scytale.roomDB.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val text: String?,
    val mobileNo: String?,
    val dateTime: Long?,
)