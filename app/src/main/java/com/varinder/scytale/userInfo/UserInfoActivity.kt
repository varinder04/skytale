package com.varinder.scytale.userInfo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.varinder.scytale.R
import com.varinder.scytale.base.BaseActivity
import com.varinder.scytale.databinding.ActivityUserInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class UserInfoActivity : BaseActivity() {
    lateinit var mBinding: ActivityUserInfoBinding
    private val viewModel: UserInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        mBinding.vm = viewModel
        Glide.with(this)
            .load(File(viewModel.image.get()))
            .into(mBinding.ivProfilePic)
    }
}