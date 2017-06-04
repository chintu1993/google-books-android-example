package com.books.sample.shared.dialog.platform

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DialogController @Inject constructor() {
    @Inject lateinit var dialogView: DialogView

    fun showTitle(title: String) {
        dialogView.showDialog(title, null)
    }

    fun showMessage(message: String) {
        dialogView.showDialog(null, message)
    }

    fun showDialog(title: String, message: String) {
        dialogView.showDialog(title, message)
    }


}