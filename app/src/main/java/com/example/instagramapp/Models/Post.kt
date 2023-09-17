package com.example.instagramapp.Models

class Post {

    var postUrl:String=""
    var caption:String=""
    var uid:String=""
    var time:String=""
    constructor()
    constructor(postUrl: String, caption: String, user: Any) {
        this.postUrl = postUrl
        this.caption = caption
    }

    constructor(postUrl: String, caption: String, uid: String, time: String) {
        this.postUrl = postUrl
        this.caption = caption
        this.uid = uid
        this.time = time
    }


}