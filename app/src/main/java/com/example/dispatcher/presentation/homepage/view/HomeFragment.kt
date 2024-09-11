package com.example.dispatcher.presentation.homepage.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dispatcher.databinding.FragmentHomeBinding
import com.example.dispatcher.presentation.homepage.viewModel.ArticlesViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: ArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        setSaveButton()
    }

    private fun subscribeObservers() {
        homeViewModel.getFirstTwoWordsLiveData().observe(viewLifecycleOwner) { twoWords ->
            binding.textViewHomeFragment.text = twoWords.toString()
        }
    }

    private fun setSaveButton() {
        // Handle the Save button click
        binding.buttonSave.setOnClickListener {
            val content = binding.editTextTitle.text.toString()
            homeViewModel.addFirstTwoWords(content)
        }
    }

    override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
    }
}
