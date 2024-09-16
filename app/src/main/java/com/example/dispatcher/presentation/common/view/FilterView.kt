package com.example.dispatcher.presentation.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dispatcher.databinding.FilterBarBinding

import com.example.dispatcher.presentation.main.MainViewModel

class FilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: FilterBarBinding = FilterBarBinding.inflate(LayoutInflater.from(context), this, true)
    private var mainViewModel: MainViewModel? = null

    init {
        binding.filterIcon.setOnClickListener {
            mainViewModel?.changeFilterDrawerDisplay(true)
        }
    }

    fun setViewModel(viewModel: MainViewModel) {
        this.mainViewModel = viewModel
    }
}
