package com.example.dispatcher.presentation.HeaderView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.dispatcher.R
import com.example.dispatcher.databinding.ViewHeaderBinding

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding: ViewHeaderBinding = ViewHeaderBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setListeners()
    }

    private fun setListeners() {
        binding.headerBellIcon.setOnClickListener {
        }

        binding.headerSearchIcon.setOnClickListener {
        }
    }



}
