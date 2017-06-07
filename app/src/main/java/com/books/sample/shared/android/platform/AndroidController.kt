package com.books.sample.shared.android.platform

import android.content.Context
import android.content.Intent
import android.support.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidController @Inject constructor() {
    lateinit var context: Context

    fun getString(@StringRes stringId: Int): String {
        return context.getString(stringId)
    }

    fun openWebIntent(uri: com.books.sample.shared.domain.Uri) {
        val browserIntent = Intent(Intent.ACTION_VIEW, uri.value)
        context.startActivity(browserIntent)
    }

    fun openShareIntent(shareContent: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, shareContent)
        intent.type = "text/plain"
        context.startActivity(intent)
    }
}