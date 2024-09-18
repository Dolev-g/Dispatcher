package com.example.dispatcher.presentation.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dispatcher.databinding.FilterBarBinding

class FilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: FilterBarBinding = FilterBarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
    }

    fun setFilterIconAction(action: () -> Unit) {
        binding.filterIcon.setOnClickListener{
            action()
        }
    }

}
