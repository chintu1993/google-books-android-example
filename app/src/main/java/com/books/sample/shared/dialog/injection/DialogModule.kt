package com.books.sample.shared.dialog.injection

import com.books.sample.shared.android.platform.AndroidController
import com.books.sample.shared.dialog.platform.DialogView
import com.books.sample.shared.dialog.ui.DialogViewImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DialogModule {
    @Provides
    @Singleton
    fun provideDialogView(androidController: AndroidController): DialogView = DialogViewImpl(androidController)
}