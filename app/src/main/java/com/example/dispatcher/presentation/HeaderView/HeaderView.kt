package com.example.dispatcher.presentation.HeaderView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.dispatcher.R

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    val imageView: ImageView
    val bellIcon: ImageView
    val searchIcon: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_header, this, true)

        imageView = findViewById(R.id.headerImageView)
        bellIcon = findViewById(R.id.headerBellIcon)
        searchIcon = findViewById(R.id.headerSearchIcon)
    }
}
