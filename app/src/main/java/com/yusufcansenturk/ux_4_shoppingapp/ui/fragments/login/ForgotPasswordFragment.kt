package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentForgotPasswordBinding
import com.yusufcansenturk.ux_4_shoppingapp.utils.*
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.sendBtn.setOnClickListener {
            if (validation()){
                viewModel.forgotPassword(binding.emailEditTxt.text.toString())
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }
    }

    private fun observer(){
        viewModel.forgotPassword.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.sendBtn.text = ""
                    binding.forgotProgress.show()
                }
                is UiState.Failure -> {
                    binding.sendBtn.text = "Send"
                    binding.forgotProgress.hide()
                    ErrorToast(requireActivity(), state.error.toString())
                }
                is UiState.Success -> {
                    binding.sendBtn.text = "Send"
                    binding.forgotProgress.hide()
                    SuccessToast(requireActivity(),state.data)
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.emailEditTxt.text.isNullOrEmpty()){
            isValid = false
            InfoToast2(requireActivity(),getString(R.string.enter_email))
        }else{
            if (!binding.emailEditTxt.text.toString().isValidEmail()){
                isValid = false
                InfoToast2(requireActivity(),getString(R.string.invalid_email))
            }
        }

        return isValid
    }

}