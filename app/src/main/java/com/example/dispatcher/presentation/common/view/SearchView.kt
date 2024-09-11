package com.example.dispatcher.presentation.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dispatcher.databinding.SearchHeaderBinding
import com.example.dispatcher.presentation.main.MainViewModel

class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: SearchHeaderBinding = SearchHeaderBinding.inflate(LayoutInflater.from(context), this, true)
    private var mainViewModel: MainViewModel? = null

    init {
        setListeners()
    }

    private fun setListeners() {
        binding.backImg.setOnClickListener {
            mainViewModel?.changeSearchStage(false)
        }

        binding.headerSearchIcon.setOnClickListener {
        }
    }

    fun setViewModel(viewModel: MainViewModel) {
        this.mainViewModel = viewModel
    }
}
