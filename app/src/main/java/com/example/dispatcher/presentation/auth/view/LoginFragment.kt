package com.example.dispatcher.presentation.auth.view

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dispatcher.R
import com.example.dispatcher.common.utils.Utils
import com.example.dispatcher.databinding.FragmentLoginBinding
import com.example.dispatcher.domain.auth.EnumNavigate
import com.example.dispatcher.presentation.auth.AuthViewModel

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
        setButtons()
        observeAuthResult()
    }

    private fun observeAuthResult() {
        authViewModel.getAuthResult().observe(viewLifecycleOwner) { result ->
            if (result.success) {
                Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                authViewModel.changeStage(EnumNavigate.MAIN)
            } else {
                binding.emailTextInputLayout.error = result.error
            }
            authViewModel.changeLoader(false)
        }
    }

    private fun clearErrors() {
        binding.emailTextInputLayout.error = null
    }

    private fun setButtons() {
        binding.signupButton.setOnClickListener {
            authViewModel.changeStage(EnumNavigate.SIGNUP)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            clearErrors()

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
                authViewModel.checkLogin(email, password)
            }
            else {
                binding.emailTextInputLayout.error = getString(R.string.invalidEmail)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
