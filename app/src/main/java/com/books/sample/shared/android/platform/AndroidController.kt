package com.books.sample.shared.android.platform

import android.content.Context
import android.support.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidController @Inject constructor() {
    lateinit var context: Context

    fun getString(@StringRes stringId: Int): String {
        return context.getString(stringId)
    }
}