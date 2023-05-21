package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentLoginBinding
import com.yusufcansenturk.ux_4_shoppingapp.prefs.AppSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appSessionManager: AppSessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            if (appSessionManager.getIsFirstRun()) {
                findNavController().navigate(R.id.action_loginFragment_to_appIntroFragment2)
            }else {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }

        binding.signupTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}