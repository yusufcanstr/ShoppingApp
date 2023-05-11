package com.yusufcansenturk.ux_4_shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.ux_4_shoppingapp.databinding.ItemProductsBinding
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem
import com.yusufcansenturk.ux_4_shoppingapp.utils.enums.HomeClickType

class ProductsAdapter(
    private var liveData: List<ProductsItem>,
    private var onItemClick: (product: ProductsItem, type: HomeClickType) -> Unit,
)
    : RecyclerView.Adapter<ProductsAdapter.MyCustomHolder>(){

    class MyCustomHolder(val binding: ItemProductsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:ProductsItem) {
            binding.txtTitle.text = data.title
            binding.txtPrice.text = "$ ${data.price.toString()}"

            Glide
                .with(binding.imgProduct)
                .load(data.image)
                .into(binding.imgProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        if (liveData == null) {
            return 0
        }else {
            return 10
        }
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData!!.get(position))
        holder.binding.apply {
            likeBtn.setOnClickListener {
                onItemClick(liveData.get(position), HomeClickType.FAVORİ)
            }
        }

    }
}