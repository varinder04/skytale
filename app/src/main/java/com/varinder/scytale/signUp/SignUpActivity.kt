package com.varinder.scytale.signUp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.varinder.scytale.R
import com.varinder.scytale.base.BaseActivity
import com.varinder.scytale.chat.ChatActivity
import com.varinder.scytale.common.showNegativeAlerter
import com.varinder.scytale.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity(), OnClickListener {
    lateinit var mBinding: ActivitySignupBinding
    val viewModel: SignUpViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        mBinding.vm = viewModel
        onClickListener()
        observers()
    }

    private fun onClickListener() {
        mBinding.btnSignUp.setOnClickListener(this)
    }

    private fun observers() {
        viewModel.onSuccess.observe(this) {
            if (it) {
                startActivity(Intent(this, ChatActivity::class.java))
                finish()
            }
        }

        viewModel.onError.observe(this) {
            showNegativeAlerter(it)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSignUp -> {
                viewModel.onSignUpClick()
            }
        }
    }
}