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

class MenProductsAdapter : RecyclerView.Adapter<MenProductsAdapter.MyCustomHolder>(){

    var liveData: List<ProductsItem>? = null

    fun setList(liveData: List<ProductsItem>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val imgProducts = view.findViewById<ImageView>(R.id.imgProduct)

        fun bind(data: ProductsItem) {
            txtTitle.text = data.title

            Glide
                .with(imgProducts)
                .load(data.image)
                .into(imgProducts)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_products_2,parent,false)
        return MyCustomHolder(view)
    }

    override fun getItemCount(): Int {
        if (liveData == null) {
            return 0
        }else {
            return 5
        }
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData!![position])
    }
}