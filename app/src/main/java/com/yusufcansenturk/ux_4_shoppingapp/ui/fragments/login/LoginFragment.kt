package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentLoginBinding
import com.yusufcansenturk.ux_4_shoppingapp.utils.*
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.forgotPassLapel.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.loginButton.setOnClickListener {
            if (validation()) {
                viewModel.login(
                    email = binding.emailEditText.text.toString(),
                    password = binding.passwordEditText.text.toString()
                )
            }
        }

        observer()
    }

    private fun observer(){
        viewModel.login.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.loginButton.text = ""
                    binding.loginProgress.show()
                }
                is UiState.Failure -> {
                    binding.loginButton.text = "Login"
                    binding.loginProgress.hide()
                    ErrorToast(requireActivity(), state.error.toString())
                }
                is UiState.Success -> {
                    binding.loginButton.text = "Login"
                    binding.loginProgress.hide()
                    SuccessToast(requireActivity(), state.data.toString())
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.emailEditText.text.isNullOrEmpty()) {
            isValid = false
            InfoToast2(requireActivity(), "Enter Email")
        }

        if (binding.passwordEditText.text.isNullOrEmpty()) {
            isValid = false
            InfoToast2(requireActivity(), "Enter Password")
        }else{
            if (binding.passwordEditText.text.toString().length < 8) {
                isValid = false
                InfoToast2(requireActivity(), "Your password must be at least 8 characters")
            }
        }
        return isValid
    }
}