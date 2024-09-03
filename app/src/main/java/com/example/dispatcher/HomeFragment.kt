package com.example.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dispatcher.databinding.FragmentHomeBinding
import com.example.dispatcher.model.Article
import com.example.dispatcher.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Instantiate the ViewModel
    private val homeViewModel: HomeViewModel by viewModels()

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

        // Observe the LiveData from the ViewModel
        homeViewModel.getFirstTwoWordsLiveData().observe(viewLifecycleOwner, Observer { TwoWords ->

            // Set the text to the TextView
            binding.textViewHomeFragment.text = TwoWords.toString()
        })

        // Handle the Save button click
        binding.buttonSave.setOnClickListener {
            val content = binding.editTextTitle.text.toString()
            homeViewModel.addFirstTwoWords(content)
            binding.editTextTitle.text.clear() // Clear the EditText after saving
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
