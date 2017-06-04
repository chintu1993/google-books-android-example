package com.books.sample.booklist.platform

import com.books.sample.booklist.domain.Book
import com.books.sample.booklist.domain.BookListResponse
import com.books.sample.booklist.domain.FilterPagingRequest
import com.books.sample.shared.datatransfer.platform.BaseController
import com.google.common.collect.Lists
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookListController @Inject constructor() : BaseController(), BookListProcessorListener {
    @Inject lateinit var listProcessor: BookListProcessor

    var bookList: MutableList<Book> = Lists.newArrayList()
    var query: String? = null
    var downloadableItemsCount: Int = 0

    fun requestBooks(query: String) {
        Timber.d("books requested")
        bookList = Lists.newArrayList()
        this.query = query
        listProcessor.request(FilterPagingRequest(query, 0), this)
    }

    fun requestMoreBooks() {
        query?.let { listProcessor.request(FilterPagingRequest(it, bookList.size), this) }
    }

    override fun onBooksRetrieved(listResponse: BookListResponse) {
        bookList.addAll(listResponse.bookList)
        downloadableItemsCount = listResponse.itemsCount

        //forward info items loaded

        if (bookList.size >= listResponse.itemsCount) {
            //forward info to more items
        }

        Timber.d("books retrieved")
    }

}