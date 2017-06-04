package com.books.sample.booklist.domain

data class PublishInfo(
        val publishedDate: String,
        val publisher: String
) {
    constructor(publishedDate: String) : this(publishedDate, "")
}