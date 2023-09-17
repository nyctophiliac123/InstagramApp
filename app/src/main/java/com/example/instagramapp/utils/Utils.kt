package com.example.instagramapp.utils

import android.app.ProgressDialog
import android.net.Uri
import com.google.api.Context
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

fun uploadImage(uri: Uri, folderName: String, callback: (String)->Unit) {
    var imageUrl: String?=null
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUrl=it.toString()
                callback(imageUrl!!)
            }
        }

}


fun uploadVideo(uri: Uri, folderName: String, context: Context, callback: (String)->Unit) {
    var imageUrl: String?=null
    var progressDialog=ProgressDialog(context).setTitle("Uploading . . .")
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUrl=it.toString()
                callback(imageUrl!!)
            }
        }

        .addOnProgressListener {

        }

}