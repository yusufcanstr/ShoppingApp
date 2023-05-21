package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.adapter.ListAdapter
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentListBinding
import com.yusufcansenturk.ux_4_shoppingapp.utils.Constants.ALL_PRODUCTS
import com.yusufcansenturk.ux_4_shoppingapp.utils.SuccessToast
import com.yusufcansenturk.ux_4_shoppingapp.utils.enums.HomeClickType
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        arguments?.let {
            val productType = ListFragmentArgs.fromBundle(it).ListType
            viewModel.loadProductsList(productType)
        }
        viewModel.getObserveLiveData(0).observe(viewLifecycleOwner) { productList ->
            productList?.let {
                binding.progressBar.visibility = View.GONE
                binding.errorImageView.visibility = View.GONE

                binding.productRecyclerView.apply {
                    adapter = ListAdapter(productList) { product, type ->
                        when(type) {
                            HomeClickType.FAVORİ -> {
                                //Ad Favorite
                                viewModel.addProductFavorites(product)
                                SuccessToast(requireActivity(), "Ürün başarılı bir şekilde favoriler listenize eklenmiştir.")
                            }
                            HomeClickType.IMAGE -> {
                                //Go to Details Fragment
                            }
                            else -> {}
                        }
                    }
                    binding.productRecyclerView.adapter = adapter
                    binding.productRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
                }
            }
        }

        viewModel.productsError.observe(viewLifecycleOwner) { isErrorMessage ->
            isErrorMessage?.let {

            }
        }

        viewModel.productsLoading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {

            }
        }

    }

    override fun onResume() {
        super.onResume()
        progressBarNotShow()
    }

    private fun progressBarNotShow() {
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