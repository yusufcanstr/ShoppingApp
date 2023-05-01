package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.home.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.adapter.MenProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.adapter.ProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.adapter.WomanProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentHomeBinding
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initRecyclerViews() {

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        productsAdapter = ProductsAdapter()
        binding.recyclerView.adapter = productsAdapter

        binding.manRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        menProductsAdapter = MenProductsAdapter()
        binding.manRecyclerView.adapter = menProductsAdapter

        binding.womanRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        womanProductsAdapter = WomanProductsAdapter()
        binding.womanRecyclerView.adapter = womanProductsAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomePageViewModel::class.java]

        initRecyclerViews()

        viewModel.loadData()

        viewModel.getObserveLiveData(0).observe(viewLifecycleOwner
        ) { t ->
            if (t != null) {
                productsAdapter.setList(t)
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