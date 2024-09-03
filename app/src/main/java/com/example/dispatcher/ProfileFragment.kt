package com.example.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dispatcher.databinding.FragmentProfileBinding
import com.example.dispatcher.viewmodel.ProfileViewModel

class ProfileFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    // Instantiate the ViewModel
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the LiveData from the ViewModel
        profileViewModel.getAuthorsLiveData().observe(viewLifecycleOwner, Observer { titles ->
            // Set the text to the TextView
            binding.textViewProfileFragment.text = titles
        })

        // Handle the Save button click
        binding.buttonSaveAuthor.setOnClickListener {
            val title = binding.addAuthorEditText.text.toString()
            profileViewModel.addAuthor(title)
            binding.addAuthorEditText.text.clear() // Clear the EditText after saving
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
