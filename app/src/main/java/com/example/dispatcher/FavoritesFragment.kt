package com.example.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dispatcher.databinding.FragmentFavoritesBinding

class FavoritesFragment : BaseFragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using view binding
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dynamically create a TextView for each title and add it to the layout
        articleList.mapNotNull { it.title }.forEach { title ->
            val textView = TextView(requireContext()).apply {
                text = title
                textSize = 18f
                setTextColor(resources.getColor(android.R.color.black, null))
            }
            // Add the TextView to the root LinearLayout
            binding.root.addView(textView)
        }

        displayToast("Favorites Fragment is active!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
