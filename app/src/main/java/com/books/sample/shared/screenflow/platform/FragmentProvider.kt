package com.books.sample.shared.screenflow.platform

import android.support.v4.app.Fragment

interface FragmentProvider {
    fun provide(): Fragment
}