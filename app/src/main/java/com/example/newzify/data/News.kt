package com.example.newzify.data

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)