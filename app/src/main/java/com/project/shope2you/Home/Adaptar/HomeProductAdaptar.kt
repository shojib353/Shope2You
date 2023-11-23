package com.project.shope2you.Home.Adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.project.shope2you.Home.DataClass.productmodel
import com.project.shope2you.R
import com.project.shope2you.databinding.ItemProduct1Binding
import com.project.shope2you.databinding.ItemProduct2Binding

class HomeProductAdaptar(var context: Context, var list: ArrayList<productmodel>):Adapter<MMyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MMyViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.item_product1,parent,false)
        return MMyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MMyViewHolder, position: Int) {
        holder.binding.productName.text=list[position].productName
        holder.binding.productMrp.text=list[position].productMrp
        holder.binding.productSp.text=list[position].productSp
        holder.binding.quantity.text=list[position].quantity
        holder.binding.quantityType.text=list[position].quantityType
        Glide.with(context).load(list[position].productCoverImage).into(holder.binding.productCoverImage)





    }
}
class MMyViewHolder(itemView: View) :ViewHolder(itemView){
    var binding= ItemProduct2Binding.bind(itemView)

}