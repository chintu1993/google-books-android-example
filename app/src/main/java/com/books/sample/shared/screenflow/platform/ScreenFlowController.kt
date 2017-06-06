package com.books.sample.shared.screenflow.platform

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenFlowController @Inject constructor() {

    @IdRes private var containerId: Int = 0
    private lateinit var fragmentManager: FragmentManager

    fun initialize(@IdRes containerId: Int, fragmentManager: FragmentManager) {
        this.containerId = containerId
        this.fragmentManager = fragmentManager
    }

    fun showFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}