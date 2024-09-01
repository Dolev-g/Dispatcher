package com.example.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dispatcher.databinding.FragmentFavoritesBinding

class HomeFragment : BaseFragment() {

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

        // Extract the first two words from each article's body and concatenate them
        val firstTwoWordsList = articleList.mapNotNull { article ->
            article.body?.split(" ")?.take(2)?.joinToString(" ")
        }.joinToString(separator = "\n")

        // Set the concatenated first two words to the TextView
        binding.textView.text = firstTwoWordsList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
