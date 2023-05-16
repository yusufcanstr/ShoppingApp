package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentDetailsBinding
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomePageViewModel
    private var ID : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomePageViewModel::class.java]
        arguments?.let {
            val productID = DetailsFragmentArgs.fromBundle(it).productId
            ID = productID
        }
        viewModel.loadSingleProduct(ID)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getSingleProductObserveLiveData().observe(viewLifecycleOwner) { productItem ->
            productItem?.let {
                binding.productTitle.text = productItem.title
                binding.productPrice.text = productItem.price.toString()
                binding.productCategory.text = productItem.category
                binding.productDescripton.text = productItem.description
                binding.productRate.text = productItem.rating.rate.toString()
                Glide.with(requireContext()).load(productItem.image).centerCrop().into(binding.productImageView)
            }
        }

        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_detailsFragment_to_homeFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView = requireActivity().findViewById<ChipNavigationBar>(R.id.navigationBar)
        val fragmentContainerView2 = requireActivity().findViewById<FragmentContainerView>(R.id.fragmentContainerView2)
        val layoutParams = fragmentContainerView2.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(0,0,0,0)
        bottomNavigationView.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}