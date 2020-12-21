package com.ilijatomic.htectestapp.ui.common

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    private lateinit var snackbar: Snackbar

    /**
     * Display Snackbar on screen with given [message] and [duration]
     */
    fun showSnackBar(view: View, message: String, duration: Int) {
        hideSnackBar()
        snackbar = Snackbar.make(view, message, duration)
        snackbar.show()
    }

    /**
     * Hides Snack bar if it is displayed on screen
     */
    fun hideSnackBar() {
        if (this::snackbar.isInitialized && snackbar.isShown) {
            snackbar.dismiss()
        }
    }

    abstract fun onActivityDestroy()
}