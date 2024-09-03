package com.example.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dispatcher.databinding.FragmentFavoritesBinding
import com.example.dispatcher.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    // Instantiate the ViewModel
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

        // Observe the LiveData from the ViewModel
        favoritesViewModel.getFavoritesLiveData().observe(viewLifecycleOwner, Observer { titles ->
            // Set the text to the TextView
            binding.textViewFavoritesFragment.text = titles
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
