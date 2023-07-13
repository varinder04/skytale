package com.varinder.scytale.chat

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.varinder.scytale.R
import com.varinder.scytale.adapter.RecyclerAdapter
import com.varinder.scytale.common.DataHandler
import com.varinder.scytale.common.SingleLiveEvent
import com.varinder.scytale.roomDB.DBRepository
import com.varinder.scytale.roomDB.entities.MessageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val dbRepository: DBRepository) : ViewModel() {

    var adapter: RecyclerAdapter<MessageModel> = RecyclerAdapter(R.layout.list_item_message)

    var onItemClick = SingleLiveEvent<Boolean>()
    val messagesList: MutableList<MessageModel> = ArrayList()

    var dataList = getAllMessages().map { list ->
        messagesList.clear()
        list.map { message ->
            messagesList.add(
                MessageModel(
                    id = message.id,
                    text = message.text,
                    mobileNo = message.mobileNo,
                    dateTime = message.dateTime
                )
            )
        }
        if (messagesList.isEmpty()) {
            DataHandler.ERROR(null, "LIST IS EMPTY OR NULL")
        } else {
            DataHandler.SUCCESS(messagesList)
        }
    }

    init {
        adapter.setOnItemClick(object : RecyclerAdapter.OnItemClick {
            override fun onClick(view: View, position: Int, type: String) {
                when (type) {
                    "userInfo" -> {
                        onItemClick.value = true
                    }
                }
            }
        })
    }


    fun insertMessage(message: MessageEntity) {
        viewModelScope.launch {
            val res = dbRepository.insert(message)
            Log.e("insert", "" + res)
        }
    }

    fun deleteMessage(message: MessageEntity) {
        viewModelScope.launch {
            dbRepository.delete(message)
        }
    }

    fun deleteAllMessages() {
        viewModelScope.launch {
            dbRepository.deleteAll()
        }
    }

    private fun getAllMessages() = dbRepository.getAllMessages()

}