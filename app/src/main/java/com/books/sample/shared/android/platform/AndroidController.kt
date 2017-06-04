package com.books.sample.shared.android.platform

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidController @Inject constructor() {
    lateinit var context: Context
}