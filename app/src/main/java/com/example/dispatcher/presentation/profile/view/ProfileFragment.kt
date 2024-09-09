package com.example.dispatcher.presentation.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dispatcher.R
import com.example.dispatcher.databinding.FragmentProfileBinding
import com.example.dispatcher.presentation.profile.viewModel.ProfileViewModel

class ProfileFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
