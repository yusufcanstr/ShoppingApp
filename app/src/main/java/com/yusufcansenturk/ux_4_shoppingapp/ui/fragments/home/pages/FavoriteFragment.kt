package com.yusufcansenturk.ux_4_shoppingapp.ui.fragments.home.pages

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufcansenturk.ux_4_shoppingapp.adapter.FavoriteAdapter
import com.yusufcansenturk.ux_4_shoppingapp.databinding.FragmentFavoriteBinding
import com.yusufcansenturk.ux_4_shoppingapp.utils.DeleteToast
import com.yusufcansenturk.ux_4_shoppingapp.utils.SuccessToast
import com.yusufcansenturk.ux_4_shoppingapp.utils.enums.FavoriteClickType
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : FavoriteViewModel
    private lateinit var adapter : FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        viewModel.allData.observe(viewLifecycleOwner) { favoriteData ->
            favoriteData?.let {
                adapter = FavoriteAdapter(favoriteData) { product, type ->
                    when(type) {
                        FavoriteClickType.FAVORİTE -> {

                        }
                        FavoriteClickType.BUY -> {
                            viewModel.addProductBasket(product)
                            SuccessToast(requireActivity(), "Ürün Sepete başarılı bir şekilde eklendi.")
                        }
                        FavoriteClickType.DELETE -> {
                            viewModel.deleteProductsFavorite(product_id = product.product_id)
                            DeleteToast(requireActivity(), "Ürün favorileri listenizden kaldırıldı!")
                        }
                        FavoriteClickType.PROCUDT -> {

                        }

                    }
                }
                binding.favoriteRecyclerView.adapter = adapter
                binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("before ->" + p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("after -> " + p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.searchSureList(p0.toString())
            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}