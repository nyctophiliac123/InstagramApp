package com.example.instagramapp.Post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.instagramapp.Models.Post
import com.example.instagramapp.R
import com.example.instagramapp.databinding.ActivityPostBinding
import com.example.instagramapp.utils.POST
import com.example.instagramapp.utils.POST_FOLDER
import com.example.instagramapp.utils.USER_PROFILE_FOLDER
import com.example.instagramapp.utils.uploadImage
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }

    var imageUrl: String?:null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) {
                url->
                if (url != null) {
                    binding.selectImage.setImageURI(uri)
                    imageUrl=url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.postButton.setOnClickListener {
            val post:Post = Post(imageUrl!!,binding.caption.editText?.text.toString())

            Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                finish()
            }
        }

    }
}