package com.recyclerview.mynews

import android.media.Image

data class Articles(
    val author : String ,
    val title : String ,
    val description : String,
    val url : String ,
    val urlToImage: String,
    val publishedAt: String
    )
