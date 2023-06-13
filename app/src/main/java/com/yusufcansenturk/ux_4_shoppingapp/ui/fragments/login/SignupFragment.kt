package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentSignupBinding
import com.yusufcansenturk.ux_4_shoppingapp.models.User
import com.yusufcansenturk.ux_4_shoppingapp.prefs.AppSessionManager
import com.yusufcansenturk.ux_4_shoppingapp.utils.*
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var sessionManager: AppSessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.signupButton.setOnClickListener {
            if (validation()){
                viewModel.register(
                    email = binding.emailEditText.text.toString(),
                    password = binding.passwordEditText.toString(),
                    user =  getUserObj()
                )
            }
        }
    }

    private fun observer() {
        viewModel.register.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.signupButton.text = ""
                    binding.registerProgress.show()
                }
                is UiState.Failure -> {
                    binding.signupButton.text = "Register"
                    binding.registerProgress.hide()
                    ErrorToast(requireActivity(),state.error.toString())
                }
                is UiState.Success -> {
                    binding.signupButton.text = "Register"
                    binding.registerProgress.hide()
                    SuccessToast(requireActivity(),state.data)
                    if (sessionManager.getIsFirstRun()){
                        findNavController().navigate(R.id.action_signupFragment_to_appIntroFragment)
                    }else{
                        findNavController().navigate(R.id.action_signupFragment_to_mainFragment)
                    }
                }
            }
        }
    }

    private fun getUserObj(): User {
        return User(
            id = "",
            first_name = binding.nameEditText.text.toString(),
            email = binding.emailEditText.text.toString()
        )
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.nameEditText.text.isNullOrEmpty()) {
            isValid = false
            SuccessToast(requireActivity(),"Enter Name")
        }

        if (binding.emailEditText.text.isNullOrEmpty()) {
            isValid = false
            SuccessToast(requireActivity(),"Enter Email")
        }

        if (binding.passwordEditText.text.isNullOrEmpty()) {
            isValid = false
            SuccessToast(requireActivity(), "Enter Password")
        }

        if (binding.password2EditText.text.isNullOrEmpty()) {
            isValid = false
            SuccessToast(requireActivity(), "Enter Confrim Password")
        }

        if (binding.passwordEditText.text.toString() != binding.password2EditText.text.toString()) {
            isValid = false
            SuccessToast(requireActivity(), "Passwords do not match!")
        }

        return isValid
    }

}