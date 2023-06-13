package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.home.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentProfileBinding
import com.yusufcansenturk.ux_4_shoppingapp.utils.InfoToast2
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel
    private lateinit var mainFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.signoutButton.setOnClickListener {
            //viewModel.logout()
        }

        binding.ordersButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_ordersFragment)
        }

        binding.addressButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_shoppingAddressFragment)
        }

        binding.helpButton.setOnClickListener {
            InfoToast2(requireActivity(), "Help buttonuna basıldı.")
        }

        binding.aboutButton.setOnClickListener {
            InfoToast2(requireActivity(), "About buttonuna basıldı.")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}