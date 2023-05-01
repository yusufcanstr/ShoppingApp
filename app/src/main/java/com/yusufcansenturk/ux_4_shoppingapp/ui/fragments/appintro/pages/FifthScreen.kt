package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.appintro.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentFifthScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FifthScreen : Fragment() {

    private var _binding: FragmentFifthScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFifthScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onResume() {
        super.onResume()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager2)
        val nextButton = activity?.findViewById<Button>(R.id.next_button)


        nextButton?.alpha = 0f
        nextButton?.isClickable = false

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}