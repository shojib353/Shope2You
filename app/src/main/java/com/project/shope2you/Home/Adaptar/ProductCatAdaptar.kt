package com.project.shope2you.Home.Adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.project.shope2you.Home.DataClass.catdata
import com.project.shope2you.Home.HomeDirections
import com.project.shope2you.R
import com.project.shope2you.databinding.CatagoryItemBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class ProductCatAdaptar(var context:Context,var list: ArrayList<catdata>):Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.catagory_item,parent,false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide.with(context).load(list[position].productCoverImage).into(holder.binding.catPImg)
        holder.binding.catPText.text=list[position].productCatagory
        holder.itemView.setOnClickListener {
            val name=list[position].productCatagory
            val bundle = bundleOf("name" to name)

           // val direction=HomeDirections.actionHome2ToSubCat()



            it.findNavController().navigate(R.id.action_home2_to_subCat,bundle)
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }

}
class MyViewHolder(itemView:View):ViewHolder(itemView){

    var binding=CatagoryItemBinding.bind(itemView)

}