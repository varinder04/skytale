package com.varinder.scytale.chat

import com.varinder.scytale.adapter.AbstractModel


data class MessageModel(
    val id: Int = 0,
    val text: String?,
    val mobileNo: String?,
    val dateTime: Long?,
) : AbstractModel()