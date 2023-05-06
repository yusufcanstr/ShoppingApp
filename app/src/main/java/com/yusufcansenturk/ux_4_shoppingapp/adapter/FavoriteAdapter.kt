package com.yusufcansenturk.ux_4_shoppingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcansenturk.ux_4_shoppingapp.R
import com.yusufcansenturk.ux_4_shoppingapp.di.dao.favorite.FavoriteData
import com.yusufcansenturk.ux_4_shoppingapp.viewmodel.FavoriteViewModel

class FavoriteAdapter(private val viewModel : FavoriteViewModel)
    : RecyclerView.Adapter<FavoriteAdapter.MyCustomHolder>() {

    var liveData: List<FavoriteData>? = null

    class MyCustomHolder(val view: View, val viewModel: FavoriteViewModel) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.product_title)
        val txtCategory = view.findViewById<TextView>(R.id.product_category)
        val txtRace = view.findViewById<TextView>(R.id.product_rate)
        val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
        val imgProduct = view.findViewById<ImageView>(R.id.imgProduct2)
        val deleteButton = view.findViewById<ImageView>(R.id.deleteButton)

        fun bind(data:FavoriteData) {
            txtTitle.text = data.title
            txtCategory.text = data.category
            txtRace.text = data.product_rate.toString()
            txtPrice.text = data.price.toString()

            Glide
                .with(imgProduct)
                .load(data.imageUrl)
                .into(imgProduct)

            deleteButton.setOnClickListener {
                viewModel.deleteProductsFavorite(data.product_id)
            }
        }

    }

    fun setList(liveData: List<FavoriteData>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_products_3,parent, false)
        return MyCustomHolder(view, viewModel)
    }

    override fun getItemCount(): Int {
        if (liveData != null) {
            return liveData!!.size
        }else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData!!.get(position))
    }

}