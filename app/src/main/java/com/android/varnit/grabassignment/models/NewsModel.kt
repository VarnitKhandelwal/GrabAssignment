package com.android.varnit.grabassignment.models

data class NewsModel(val status: String, val totalResults: Int, val articles: List<ArticleModel>)

data class ArticleModel(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(val id: String, val name: String)