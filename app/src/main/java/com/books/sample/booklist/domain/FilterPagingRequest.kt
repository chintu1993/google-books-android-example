package com.books.sample.booklist.domain

import com.books.sample.shared.paging.domain.PagingRequest

data class FilterPagingRequest(
        val titleFilter: String,
        override val startIndex: Int) : PagingRequest