package com.varinder.scytale.chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.varinder.scytale.R
import com.varinder.scytale.base.BaseActivity
import com.varinder.scytale.common.DataHandler
import com.varinder.scytale.common.MOBILE_NO
import com.varinder.scytale.common.SharedPrefModule
import com.varinder.scytale.common.decryptAES
import com.varinder.scytale.common.encryptAES
import com.varinder.scytale.common.showNegativeAlerter
import com.varinder.scytale.databinding.ActivityChatBinding
import com.varinder.scytale.roomDB.entities.MessageEntity
import com.varinder.scytale.userInfo.UserInfoActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.crypto.SecretKey


@AndroidEntryPoint
class ChatActivity : BaseActivity(), OnClickListener {

    lateinit var mBinding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var currentUserMobile: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        mBinding.vm = viewModel
        currentUserMobile =
            SharedPrefModule().getPref(applicationContext).getString(MOBILE_NO, "") ?: ""
        onClickListener()
        observables()
    }

    private fun onClickListener() {
        mBinding.btnSend.setOnClickListener(this)
    }

    private fun observables() {
        viewModel.onItemClick.observe(this) {
            if (it) {
                startActivity(Intent(this, UserInfoActivity::class.java))
            }
        }

        viewModel.dataList.observe(this) { dataHandler ->
            when (dataHandler) {
                is DataHandler.ERROR -> {
                    if (dataHandler.message != null)
                        showNegativeAlerter(dataHandler.message)
                }

                is DataHandler.LOADING -> {

                }

                is DataHandler.SUCCESS -> {
                    if (dataHandler.data != null)
                        viewModel.adapter.addItems(dataHandler.data)
                    mBinding.recyclerMessages.scrollToPosition(
                        mBinding.recyclerMessages.adapter?.itemCount
                            ?: 0
                    )
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSend -> {
                if (mBinding.etMessage.text.toString().isNotBlank())
                    sendMessage(mBinding.etMessage.text.toString())
            }
        }
    }

    private fun sendMessage(text: String) {
        Log.d("mobile", "" + currentUserMobile)
        var messageEntity =
            MessageEntity(
                text = text.encryptAES(),
                mobileNo = currentUserMobile,
                dateTime = System.currentTimeMillis()
            )
        viewModel.insertMessage(messageEntity)

        mBinding.etMessage.setText("")
    }
}