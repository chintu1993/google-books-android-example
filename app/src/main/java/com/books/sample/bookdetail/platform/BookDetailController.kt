package com.books.sample.bookdetail.platform

import com.books.sample.bookdetail.ui.BookDetailFragment
import com.books.sample.booklist.domain.Book
import com.books.sample.shared.android.platform.AndroidController
import com.books.sample.shared.screenflow.platform.ScreenFlowController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookDetailController @Inject constructor() {

    private lateinit var book: Book

    @Inject lateinit var screenFlowController: ScreenFlowController
    @Inject lateinit var androidController: AndroidController

    fun showBookDetail(book: Book) {
        this.book = book
        screenFlowController.showFragment(BookDetailFragment())
    }

    fun getBook() = book

    fun redirectToBookPurchase() {
        book.buyUri.orNull()?.let { androidController.openWebIntent(it) }
    }

    fun shareBook() {
        androidController.openShareIntent(book.previewLink)
    }

}