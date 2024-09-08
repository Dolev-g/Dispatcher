package com.example.dispatcher.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.dispatcher.R
import com.example.dispatcher.common.base.AuthActivity
import com.example.dispatcher.common.base.MainActivity
import com.example.dispatcher.databinding.FragmentFavoritesBinding
import com.example.dispatcher.databinding.FragmentLoginBinding
import com.example.dispatcher.presentation.auth.AuthResult
import com.example.dispatcher.presentation.favorites.viewModel.AuthViewModel
import com.example.dispatcher.presentation.favorites.viewModel.FavoritesViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSignupButton()
        setLoginButton()
        observeAuthResult()

    }

    private fun observeAuthResult() {
        authViewModel.authResult.observe(viewLifecycleOwner) { result ->
            if (result.success) {
                Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                navigateToMainActivity()
                authViewModel.changeLoader(false)
            } else {
                authViewModel.changeLoader(false)
                Toast.makeText(requireContext(), result.error ?: "Login failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setLoginButton () {
        binding.loginButton.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                authViewModel.changeLoader(true)
                authViewModel.checkLogin(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSignupButton () {
        binding.signupButton.setOnClickListener {
            authViewModel.changeStage("signup")
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
