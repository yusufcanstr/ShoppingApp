package com.yusufcansenturk.ux_4_shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.ux_4_shoppingapp.databinding.ItemProducts3Binding
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteData
import com.yusufcansenturk.ux_4_shoppingapp.utils.enums.CartClickType
import com.yusufcansenturk.ux_4_shoppingapp.utils.enums.FavoriteClickType

class FavoriteAdapter(
    private var liveData: List<FavoriteData>,
    private var onItemClick: (product: FavoriteData, type: FavoriteClickType) -> Unit,
) : RecyclerView.Adapter<FavoriteAdapter.MyCustomHolder>() {

    class MyCustomHolder(val binding: ItemProducts3Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:FavoriteData) {
            binding.productTitle.text = data.title
            binding.productCategory.text = data.category
            binding.productRate.text = data.product_rate.toString()
            binding.txtPrice.text = data.price.toString()

            Glide
                .with(binding.imgProduct2)
                .load(data.imageUrl)
                .into(binding.imgProduct2)



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = ItemProducts3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        if (liveData != null) {
            return liveData.size
        }else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData!!.get(position))
        holder.binding.apply {
            deleteButton.setOnClickListener {
                onItemClick(liveData.get(position), FavoriteClickType.DELETE)
            }

            sepeteEkleButton.setOnClickListener {
                onItemClick(liveData.get(position), FavoriteClickType.BUY)
            }
        }
    }

}