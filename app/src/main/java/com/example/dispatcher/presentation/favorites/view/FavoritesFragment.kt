package com.example.dispatcher.presentation.favorites.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dispatcher.R
import com.example.dispatcher.databinding.FragmentFavoritesBinding
import com.example.dispatcher.presentation.favorites.viewModel.FavoritesViewModel

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        onClickButtonSave()
    }

    private fun subscribeObservers() {
        favoritesViewModel.getFavoritesLiveData().observe(viewLifecycleOwner, Observer { titles ->
            binding.textViewFavoritesFragment.text = titles
        })
    }

    private fun onClickButtonSave() {
        binding.buttonSaveTitle.setOnClickListener {
            val title = binding.addTitleEditText.text.toString()
            favoritesViewModel.addTitle(title)
            binding.addTitleEditText.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
