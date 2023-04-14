package com.yusufcansenturk.ux_4_shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yusufcansenturk.ux_4_shoppingapp.databinding.ActivityMainBinding
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