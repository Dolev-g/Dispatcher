package com.example.dispatcher.presentation.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dispatcher.R
import com.example.dispatcher.common.base.AuthActivity
import com.example.dispatcher.databinding.FragmentFavoritesBinding
import com.example.dispatcher.databinding.FragmentLoginBinding
import com.example.dispatcher.presentation.favorites.viewModel.FavoritesViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // private val favoritesViewModel: FavoritesViewModel by viewModels() change to login vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSignupButton()

    }

    private fun setSignupButton () {
        binding.signupButton.setOnClickListener {
            (requireActivity() as AuthActivity).replaceFragment(SignupFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
