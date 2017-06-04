package com.books.sample.booklist.domain

import com.books.sample.shared.domain.Amount
import com.books.sample.shared.domain.Author
import com.books.sample.shared.domain.Uri
import com.google.common.base.Optional

data class Book(
        val id: String,
        val eTag: String,
        val title: String,
        val subtitle: Optional<String>,
        val description: Optional<String>,
        val pageCount: Int,
        val language: String,
        val rating: Optional<Rating>,
        val authors: List<Author>,
        val publishInfo: PublishInfo,
        val buyUri: Optional<Uri>,
        val thumbnail: Uri,
        val price: Optional<Amount>
)
