package com.yusufcansenturk.ux_4_shoppingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.models.ProductsItem

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.MyCustomHolder>(){

    var liveData: List<ProductsItem>? = null

    fun setList(liveData: List<ProductsItem>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
        val imgProducts = view.findViewById<ImageView>(R.id.imgProduct)

        fun bind(data:ProductsItem) {
            txtTitle.text = data.title
            txtPrice.text = "$ ${data.price.toString()}"

            Glide
                .with(imgProducts)
                .load(data.image)
                .into(imgProducts)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_products,parent,false)
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
    }
}