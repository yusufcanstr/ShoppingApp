package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentLoginBinding
import com.yusufcansenturk.ux_4_shoppingapp.prefs.AppSessionManager
import com.yusufcansenturk.ux_4_shoppingapp.utils.AuthResource
import com.yusufcansenturk.ux_4_shoppingapp.utils.ErrorToast
import com.yusufcansenturk.ux_4_shoppingapp.utils.SuccessToast
import com.yusufcansenturk.ux_4_shoppingapp.utils.WarningToast
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appSessionManager: AppSessionManager
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.viewModelScope.launch {
            viewModel.loginFlow.collect { resource ->
                when(resource) {
                    is AuthResource.Failure -> ErrorToast(requireActivity(), "Giriş yapılırken hata oluştu")
                    is AuthResource.Loading -> "Yükleniyor ..."
                    is AuthResource.Success -> {
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    }
                    else -> {}
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.loginButton.setOnClickListener {
            loginButtonFun()
        }

    }

    private fun loginButtonFun() {
        val email : String = binding.emailEditText.text.toString()
        val password: String = binding.passwordEditText.text.toString()
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            WarningToast(requireActivity(), "Email ve şifrenizi giriniz ve Tekrar deneyiniz")
        }else {
            viewModel.login(email, password)
            SuccessToast(requireActivity(), "Giriş başarı ile yapıldı.")
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}