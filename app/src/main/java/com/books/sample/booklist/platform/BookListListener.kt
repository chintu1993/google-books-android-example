package com.books.sample.booklist.platform

import com.books.sample.booklist.domain.Book

interface BookListListener {
    fun onBooksRequested()
    fun onClearBooks()
    fun onBooksRetrieved(bookList: List<Book>)
}