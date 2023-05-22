package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.login

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentSignupBinding
import com.yusufcansenturk.ux_4_shoppingapp.prefs.AppSessionManager
import com.yusufcansenturk.ux_4_shoppingapp.utils.SuccessToast
import com.yusufcansenturk.ux_4_shoppingapp.utils.WarningToast
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appSessionManager: AppSessionManager
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.signupButton.setOnClickListener {
            signupButtonFun()
        }

        binding.loginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

    }

    private fun signupButtonFun() {
        val name: String = binding.nameEditText.text.toString()
        val email: String = binding.emailEditText.text.toString()
        val password: String = binding.passwordEditText.text.toString()
        val password2: String = binding.password2EditText.text.toString()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2) || TextUtils.isEmpty(name)) {
            WarningToast(requireActivity(), "Email ve şifrenizi giriniz ve tekrar deneyiniz")
        } else {
            viewModel.signup(name, email, password)
            SuccessToast(requireActivity(), "Hoşgeldiniz, uygulamaya üyeliğiniz başarılı bir şekilde yapılmıştır.")

            if (appSessionManager.getIsFirstRun()) {
                findNavController().navigate(R.id.action_signupFragment_to_appIntroFragment)
            } else {
                findNavController().navigate(R.id.action_signupFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}