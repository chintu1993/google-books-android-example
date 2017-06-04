package com.books.sample.shared.datatransfer.platform

import com.books.sample.R
import com.books.sample.shared.android.platform.AndroidController
import com.books.sample.shared.dialog.platform.DialogController
import javax.inject.Inject

abstract class BaseController : ProcessorListener {
    @Inject lateinit var dialogController: DialogController
    @Inject lateinit var androidController: AndroidController

    override fun onNoConnection() {
        dialogController.showDialog(androidController.getString(R.string.global__ui__error), androidController.getString(R.string.global__ui__no_connection))
    }
}