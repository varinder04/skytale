package com.varinder.scytale.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varinder.scytale.roomDB.DBRepository
import com.varinder.scytale.roomDB.entities.MessageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val dbRepository: DBRepository) :ViewModel() {
    fun insertMessage(message: MessageEntity) {
        viewModelScope.launch {
            dbRepository.insert(message)
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

    fun getAllArticles() = dbRepository.getAllMessages()

}