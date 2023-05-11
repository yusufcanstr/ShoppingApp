package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.home.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufcansenturk.ux_4_shoppingapp.adapter.BasketAdapter
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentShoppingCartBinding
import com.yusufcansenturk.ux_4_shoppingapp.utils.enums.CartClickType
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartFragment : Fragment() {

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[BasketViewModel::class.java]
        viewModel.getList(requireContext())
        ObserveLiveData()
        return view
    }

    private fun ObserveLiveData() {
        viewModel.basketList.observe(viewLifecycleOwner) { basketData ->
            binding.recyclerViewBasket.apply {
                adapter = BasketAdapter(basketData) { product, type ->
                    when(type) {
                        CartClickType.DELETE -> {
                            viewModel.deleteProductsBasket(product)
                        }
                        CartClickType.ADD -> {
                            viewModel.addProductsBasket(product)
                        }
                        CartClickType.BOOST -> {
                            viewModel.increaseQuantity(product)
                        }
                        CartClickType.DECREASE -> {
                            viewModel.decreaseQuantity(product)
                        }
                    }
                }
                binding.recyclerViewBasket.adapter = adapter
                binding.recyclerViewBasket.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

        }

        viewModel.basketPrice.observe(viewLifecycleOwner) { totalPrice ->
            binding.txtTotalPrice.text = "$totalPrice $"
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}