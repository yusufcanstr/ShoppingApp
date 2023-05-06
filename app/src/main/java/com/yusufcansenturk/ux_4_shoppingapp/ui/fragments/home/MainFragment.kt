package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentMainBinding
import com.yusufcansenturk.ux_4_shoppingapp.prefs.AppSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appSessionManager: AppSessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        if (appSessionManager.getIsFirstRun()) {
            appSessionManager.setIsFirstRun(false)
        }

        setupTabBar()

        return view
    }

    private fun setupTabBar() {

        binding.navigationBar.setItemSelected(R.id.item_home, true)
        binding.navigationBar.setOnItemSelectedListener{
            when(it) {
                R.id.item_home -> {
                    childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.homeFragment)
                }
                R.id.item_favorite -> {
                    childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.favoriteFragment)
                }
                R.id.item_profile -> {
                    childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.profileFragment)
                }
                R.id.item_shoppincard -> {
                    childFragmentManager.primaryNavigationFragment?.findNavController()?.navigate(R.id.shoppingCartFragment)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}