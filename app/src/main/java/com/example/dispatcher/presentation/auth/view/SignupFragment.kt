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
                Toast.makeText(requireContext(), "Password doesn't match re-Enter password", Toast.LENGTH_SHORT).show()
            }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (!Utils.isValidEmail(email)) {
                    binding.emailTextInputLayout.error = getString(R.string.invalidEmail)
                }

                if(password.length < 7) {
                    binding.passwordTextInputLayout.error = getString(R.string.shortPassword)
                }

                if (!Utils.isValidPassword(password)) {
                    binding.passwordTextInputLayout.error = getString(R.string.invalidPassword)
                }

                authViewModel.changeLoader(true)
                authViewModel.createAccount(email, password)
            } else {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
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
