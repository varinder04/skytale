package com.varinder.scytale.signUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.varinder.scytale.R
import com.varinder.scytale.base.BaseActivity
import com.varinder.scytale.chat.ChatActivity
import com.varinder.scytale.common.MOBILE_NO
import com.varinder.scytale.common.SharedPrefModule
import com.varinder.scytale.common.getRealPathFromURI
import com.varinder.scytale.common.showNegativeAlerter
import com.varinder.scytale.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class SignUpActivity : BaseActivity(), OnClickListener {
    lateinit var mBinding: ActivitySignupBinding
    private val viewModel: SignUpViewModel by viewModels()

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                contentResolver.takePersistableUriPermission(uri, flag)

                viewModel.image.set(getRealPathFromURI(this, uri) ?: "")
                Glide.with(this)
                    .load(File(viewModel.image.get()))
                    .into(mBinding.ivProfilePic)





                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        mBinding.vm = viewModel
        onClickListener()
        observers()
        if ((SharedPrefModule().getPref(applicationContext).getString(MOBILE_NO, "")
                ?: "").isNotBlank()
        ) {
           viewModel.onSuccess.value=true
        }
    }

    private fun onClickListener() {
        mBinding.btnSignUp.setOnClickListener(this)
        mBinding.ivProfilePic.setOnClickListener(this)
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

            R.id.ivProfilePic -> {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }
}