package com.books.sample.shared.dialog.ui

import com.afollestad.materialdialogs.MaterialDialog
import com.books.sample.shared.android.platform.AndroidController
import com.books.sample.shared.dialog.platform.DialogView

class DialogViewImpl constructor(val androidController: AndroidController) : DialogView {

    override fun showDialog(title: String?, description: String?) {
        MaterialDialog.Builder(androidController.context).title(title ?: "").content(description ?: "").show()
    }

}