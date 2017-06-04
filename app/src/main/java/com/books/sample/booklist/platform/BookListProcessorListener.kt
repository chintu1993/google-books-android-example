package com.books.sample.booklist.platform

import com.books.sample.booklist.domain.BookListResponse
import com.books.sample.shared.datatransfer.platform.ProcessorListener

interface BookListProcessorListener : ProcessorListener {
    fun onBooksRetrieved(listResponse: BookListResponse)
}