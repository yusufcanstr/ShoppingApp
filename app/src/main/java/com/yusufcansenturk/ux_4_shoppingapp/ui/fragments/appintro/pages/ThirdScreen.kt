package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.appintro.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentThirdScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdScreen : Fragment() {

    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onResume() {
        super.onResume()
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager2)
        val nextButton = activity?.findViewById<Button>(R.id.next_button)


        nextButton?.setOnClickListener {
            viewPager?.currentItem = 3
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}