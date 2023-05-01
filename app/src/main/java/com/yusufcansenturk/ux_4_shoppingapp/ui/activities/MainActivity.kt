package com.yusufcansenturk.ux_4_shoppingapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufcansenturk.ux_4_shoppingapp.adapter.MenProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.adapter.ProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.adapter.WomanProductsAdapter
import com.yusufcansenturk.ux_4_shoppingapp.databinding.ActivityMainBinding
import com.yusufcansenturk.ux_4_shoppingapp.models.Products
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}