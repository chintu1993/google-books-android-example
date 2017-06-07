package com.books.sample.bookdetail.platform

import com.books.sample.booklist.domain.Book
import com.books.sample.shared.android.platform.AndroidController
import com.books.sample.shared.domain.Uri
import com.books.sample.shared.screenflow.platform.ScreenFlowController
import com.google.common.base.Optional
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookDetailControllerTest {
    @Mock lateinit var anyScreenFlowController: ScreenFlowController
    @Mock lateinit var anyAndroidController: AndroidController
    @Mock lateinit var anyProvider: BookDetailFragmentProvider

    @InjectMocks lateinit var detailController: BookDetailController

    @Test
    fun shouldShowBookScreen() {
        val anyBook = mock<Book>()
        detailController.showBookDetail(anyBook)
        verify(anyScreenFlowController).showFragment(anyProvider)
    }

    @Test
    fun shouldRedirect() {
        val anyBook = mock<Book>()
        val anyUri = mock<Uri>()
        whenever(anyBook.buyUri).thenReturn(Optional.of(anyUri))
        detailController.showBookDetail(anyBook)
        detailController.redirectToBookPurchase()
        verify(anyAndroidController).openWebIntent(anyUri)
    }

    @Test
    fun shouldShare() {
        val anyBook = mock<Book>()
        val anyLink = "Any_String"
        whenever(anyBook.previewLink).thenReturn(anyLink)
        detailController.showBookDetail(anyBook)
        detailController.shareBook()
        verify(anyAndroidController).openShareIntent(anyLink)
    }
}