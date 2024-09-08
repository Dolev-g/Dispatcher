package com.example.dispatcher.presentation.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dispatcher.R
import com.example.dispatcher.common.base.AuthActivity
import com.example.dispatcher.databinding.FragmentFavoritesBinding
import com.example.dispatcher.databinding.FragmentLoginBinding
import com.example.dispatcher.databinding.FragmentSignupBinding
import com.example.dispatcher.presentation.auth.AuthResult
import com.example.dispatcher.presentation.favorites.viewModel.AuthViewModel
import com.example.dispatcher.presentation.favorites.viewModel.FavoritesViewModel

class SignupFragment : Fragment(R.layout.fragment_signup) {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoginButton()
        setSignupButton()
        observeAuthResult()
    }

    private fun observeAuthResult() {
        authViewModel.authResult.observe(viewLifecycleOwner) { result ->
            if (result.success) {
                Toast.makeText(requireContext(), "signup successful!", Toast.LENGTH_SHORT).show()
                authViewModel.changeLoader(false)
                authViewModel.changeStage("login")
            } else {
                Toast.makeText(requireContext(), result.error ?: "An unknown error occurred", Toast.LENGTH_SHORT).show()
                authViewModel.changeLoader(false)
            }
        }
    }

    private fun setSignupButton() {
        binding.signupButton.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val reEnterPassword = binding.reEnterTextPassword.text.toString()

            if (password != reEnterPassword) {
                Toast.makeText(requireContext(), "Password doesn't match re-Enter password", Toast.LENGTH_SHORT).show()
            }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                authViewModel.changeLoader(true)
                authViewModel.createAccount(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setLoginButton () {
        binding.loginButton.setOnClickListener {
            authViewModel.changeStage("login")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
