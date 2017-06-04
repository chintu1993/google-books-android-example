package com.books.sample.booklist.domain

import org.joda.time.LocalDate

data class PublishInfo(
        val publishedDate: LocalDate,
        val publisher: String = ""
)