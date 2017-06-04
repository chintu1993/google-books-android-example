package com.books.sample.booklist.platform

import com.books.sample.booklist.domain.FilterPagingRequest
import com.books.sample.shared.datatransfer.platform.RestProcessor

interface BookListProcessor : RestProcessor<FilterPagingRequest, BookListProcessorListener>