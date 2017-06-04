package com.books.sample.booklist.domain

data class BookListResponse(
        val bookList: List<Book>,
        val itemsCount: Int
)