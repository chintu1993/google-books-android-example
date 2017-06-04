package com.books.sample.shared.domain

import android.net.Uri

data class Uri(override val value: Uri) : WrappedValue<Uri>
