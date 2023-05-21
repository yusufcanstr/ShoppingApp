package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentSignupBinding
import com.yusufcansenturk.ux_4_shoppingapp.prefs.AppSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appSessionManager: AppSessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.loginButton.setOnClickListener {
            if (appSessionManager.getIsFirstRun()) {
                findNavController().navigate(R.id.action_signupFragment_to_appIntroFragment)
            }else {
                findNavController().navigate(R.id.action_signupFragment_to_mainFragment)
            }
        }

        binding.loginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}