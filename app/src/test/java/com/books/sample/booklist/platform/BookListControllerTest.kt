package com.books.sample.booklist.platform

import com.books.sample.bookdetail.platform.BookDetailController
import com.books.sample.booklist.domain.Book
import com.books.sample.booklist.domain.BookListResponse
import com.books.sample.booklist.domain.FilterPagingRequest
import com.google.common.collect.Lists
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class BookListControllerTest {
    @Mock lateinit var anyProcessor: BookListProcessor
    @Mock lateinit var anyDetailController: BookDetailController
    @Mock lateinit var anyListener: BookListListener

    @InjectMocks lateinit var listController: BookListController

    @Before
    fun setUp() {
        listController.setBookListListener(anyListener)
    }


    @Test
    fun shouldRequestMoreBooks() {
        listController.requestMoreBooks()
        verify(anyListener).onBooksRequested()
        verify(anyProcessor).request(any(), eq(listController))
    }

    @Test
    fun shouldRequestDetail() {
        val anyBook = mock<Book>()
        listController.showBookDetail(anyBook)
        verify(anyDetailController).showBookDetail(anyBook)
    }

    @Test
    fun shouldForwardRetrieved() {
        val anyBook = mock<Book>()
        val anyBookList = Lists.newArrayList<Book>(anyBook)
        val anyResponse = mock<BookListResponse>()
        whenever(anyResponse.bookList).thenReturn(anyBookList)
        listController.onBooksRetrieved(anyResponse)

        verify(anyListener).onBooksRetrieved(anyBookList)
        assertEquals(anyBookList, listController.bookList)
    }


    @Test
    fun shouldRequestNewList() {
        val request = "request"
        listController.requestBooks(request)
        verify(anyListener).onClearBooks()
        verify(anyListener).onBooksRequested()
        val requestCaptor = argumentCaptor<FilterPagingRequest>()
        verify(anyProcessor).request(requestCaptor.capture(), eq(listController))

        assertEquals(requestCaptor.firstValue.titleFilter, request)
        assertEquals(requestCaptor.firstValue.startIndex, 0)
    }
}