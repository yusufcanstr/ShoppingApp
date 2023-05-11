package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.home.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufcansenturk.ux_4_shoppingapp.adapter.MenProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.adapter.ProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.adapter.WomanProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentHomeBinding
import com.yusufcansenturk.ux_4_shoppingapp.utils.enums.HomeClickType
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var menProductsAdapter: MenProductsAdapter
    private lateinit var womanProductsAdapter: WomanProductsAdapter

    private lateinit var viewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initRecyclerViews() {
        binding.manRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        menProductsAdapter = MenProductsAdapter()
        binding.manRecyclerView.adapter = menProductsAdapter

        binding.womanRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        womanProductsAdapter = WomanProductsAdapter()
        binding.womanRecyclerView.adapter = womanProductsAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomePageViewModel::class.java]

        initRecyclerViews()

        viewModel.loadData()

        viewModel.getObserveLiveData(0).observe(viewLifecycleOwner) { productList ->
            productList?.let {
                binding.recyclerView.apply {
                    adapter = ProductsAdapter(productList) { product, type ->
                        when(type) {
                            HomeClickType.FAVORİ -> {
                                viewModel.addFavoriteProduct(product)
                                Toast.makeText(requireContext(), "Favorilerden Eklendi", Toast.LENGTH_SHORT).show()
                            }
                            HomeClickType.IMAGE -> {
                                //Go to Details Fragment
                            }
                            else -> {}
                        }
                    }
                    binding.recyclerView.adapter = adapter
                    binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }

        }

        viewModel.getObserveLiveData(1).observe(viewLifecycleOwner
        ) { t ->
            if (t != null) {
                menProductsAdapter.setList(t)
            }
        }

        viewModel.getObserveLiveData(2).observe(viewLifecycleOwner
        ) { t ->
            if (t != null) {
                womanProductsAdapter.setList(t)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}