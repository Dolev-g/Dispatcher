package com.example.dispatcher.presentation.auth.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dispatcher.R
import com.example.dispatcher.common.utils.Utils
import com.example.dispatcher.databinding.FragmentSignupBinding
import com.example.dispatcher.domain.auth.EnumNavigate
import com.example.dispatcher.presentation.auth.AuthViewModel

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
        authViewModel.getAuthResult().observe(viewLifecycleOwner) { result ->
            if (result.success) {
                Toast.makeText(requireContext(), "signup successful!", Toast.LENGTH_SHORT).show()
                authViewModel.changeLoader(false)
                authViewModel.changeStage(EnumNavigate.LOGIN)
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
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Utils.isValidEmail(email)) {
                binding.emailTextInputLayout.error = getString(R.string.invalidEmail)
                return@setOnClickListener
            }

            if (password.length < 7) {
                binding.passwordTextInputLayout.error = getString(R.string.shortPassword)
                return@setOnClickListener
            }

            if (!Utils.isValidPassword(password)) {
                binding.passwordTextInputLayout.error = getString(R.string.invalidPassword)
                return@setOnClickListener
            }

            authViewModel.changeLoader(true)
            authViewModel.createAccount(email, password)
        }
    }


    private fun setLoginButton () {
        binding.loginButton.setOnClickListener {
            authViewModel.changeStage(EnumNavigate.LOGIN)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
