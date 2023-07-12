package com.varinder.scytale.chat

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.databinding.DataBindingUtil
import com.varinder.scytale.R
import com.varinder.scytale.base.BaseActivity
import com.varinder.scytale.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : BaseActivity(), OnClickListener {

    lateinit var mBinding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        onClickListener()
    }

    private fun onClickListener() {
        mBinding.btnSend.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSend -> {

            }
        }
    }
}