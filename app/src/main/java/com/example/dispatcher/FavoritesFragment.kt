package com.example.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // Filter the titles from articleList and concatenate them
        val titles = articleList.mapNotNull { it.title }.joinToString(separator = "\n")

        binding.textView.text = titles

        displayToast("Favorites Fragment is active!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
