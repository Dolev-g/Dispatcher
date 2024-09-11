package com.example.dispatcher.presentation.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.dispatcher.databinding.ViewHeaderBinding
import com.example.dispatcher.presentation.main.MainViewModel

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding: ViewHeaderBinding = ViewHeaderBinding.inflate(LayoutInflater.from(context), this, true)
    private var mainViewModel: MainViewModel? = null

    init {
        setListeners()
    }

    private fun setListeners() {
        binding.headerBellIcon.setOnClickListener {
        }

        binding.headerSearchIcon.setOnClickListener {
            mainViewModel?.changeSearchStage(true)
        }
    }

    fun setViewModel(viewModel: MainViewModel) {
        this.mainViewModel = viewModel
    }
}
