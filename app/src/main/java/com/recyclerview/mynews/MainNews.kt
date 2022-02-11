package com.recyclerview.mynews

import java.sql.ClientInfoStatus

data class MainNews(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)
