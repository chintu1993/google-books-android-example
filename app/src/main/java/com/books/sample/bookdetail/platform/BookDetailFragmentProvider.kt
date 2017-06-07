package com.books.sample.bookdetail.platform

import com.books.sample.bookdetail.ui.BookDetailFragment
import com.books.sample.shared.screenflow.platform.FragmentProvider
import javax.inject.Inject

class BookDetailFragmentProvider @Inject constructor() : FragmentProvider {
    override fun provide() = BookDetailFragment()
}