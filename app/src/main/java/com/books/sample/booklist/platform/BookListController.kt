package com.books.sample.booklist.platform

import com.books.sample.bookdetail.platform.BookDetailController
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
    private val defaultQuery = "Kitty"

    @Inject lateinit var listProcessor: BookListProcessor
    @Inject lateinit var detailController: BookDetailController

    var bookList: MutableList<Book> = Lists.newArrayList()
    var query: String = defaultQuery
    var downloadableItemsCount: Int = 0
    private var listener: BookListListener? = null

    fun setBookListListener(listener: BookListListener) {
        this.listener = listener
    }

    fun requestBooks(query: String) {
        listener?.onClearBooks()
        if (this.query == query && !bookList.isEmpty()) {
            Timber.d("no new query needed - %s", query)
            listener?.onBooksRetrieved(bookList)
        } else {
            Timber.d("Books query requested - %s", query)
            listener?.onBooksRequested()
            bookList = Lists.newArrayList()
            this.query = query
            listProcessor.request(FilterPagingRequest(query, 0), this)
        }
    }

    fun requestMoreBooks() {
        listener?.onBooksRequested()
        listProcessor.request(FilterPagingRequest(query, bookList.size), this)
    }

    override fun onBooksRetrieved(listResponse: BookListResponse) {
        listener?.onBooksRetrieved(listResponse.bookList)
        bookList.addAll(listResponse.bookList)
        downloadableItemsCount = listResponse.itemsCount

        if (bookList.size >= listResponse.itemsCount) {
            //forward info to more items
        }

        Timber.d("books retrieved")
    }

    fun showBookDetail(book: Book) {
        detailController.showBookDetail(book)
    }

}