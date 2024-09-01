package com.example.dispatcher

import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.View

fun Fragment.displayToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun View.showView(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}