package com.yusufcansenturk.ux_4_shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.ux_4_shoppingapp.databinding.ItemBasketBinding
import com.yusufcansenturk.ux_4_shoppingapp.databinding.ItemProducts3Binding
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.basket.BasketData
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteData
import com.yusufcansenturk.ux_4_shoppingapp.utils.enums.CartClickType
import java.text.NumberFormat
import java.util.*

class BasketAdapter(
    private var liveData: List<BasketData>,
    private var onItemClick: (product: BasketData, type: CartClickType) -> Unit,
) : RecyclerView.Adapter<BasketAdapter.MyCustomHolder>() {


    class MyCustomHolder(val binding:ItemBasketBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data:BasketData) {
            binding.productTitle.text = data.title
            binding.productRate.text = data.product_rate.toString()

            val formatter = NumberFormat.getCurrencyInstance(Locale("tr", "TR"))
            formatter.maximumFractionDigits = 2
            val formattedPrice = formatter.format(data.price)
            binding.txtPrice.text = formattedPrice
            binding.productSize.text = data.productQuantity.toInt().toString()

            Glide
                .with(binding.imageProduct)
                .load(data.imageUrl)
                .into(binding.imageProduct)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val rcvBasket = ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(rcvBasket)
    }

    override fun getItemCount(): Int {
        if (liveData != null) {
            return liveData.size
        }else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData.get(position))
        holder.binding.apply {
            deleteButton.setOnClickListener {
                onItemClick(liveData.get(position), CartClickType.DELETE)
            }

            boostButton.setOnClickListener {
                onItemClick(liveData.get(position), CartClickType.BOOST)
            }

            decreaseButton.setOnClickListener {
                onItemClick(liveData.get(position), CartClickType.DECREASE)
            }
        }
    }
}