package com.varinder.scytale.userInfo

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.varinder.scytale.R
import com.varinder.scytale.base.BaseActivity
import com.varinder.scytale.databinding.ActivityUserInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoActivity : BaseActivity() {
    lateinit var mBinding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
    }
}