package com.project.shope2you.subCat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

import com.project.shope2you.Home.DataClass.subcatdata
import com.project.shope2you.R

import com.project.shope2you.databinding.SubCatItemBinding

class SubCatAdaptar(var context: Context, var list: ArrayList<subcatdata>) :Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view= LayoutInflater.from(context).inflate(R.layout.sub_cat_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.SubCatPText.text=list[position].subCatagoryName
        Glide.with(context).load(list[position].subCatagoryCoverImage).into(holder.binding.SubCatPImg)

        holder.itemView.setOnClickListener {
            val name=list[position].subCatagoryName
            val bundle = bundleOf("name" to name)

            // val direction=HomeDirections.actionHome2ToSubCat()



            it.findNavController().navigate(R.id.action_subCat_to_product,bundle)
        }



    }
}
class MyViewHolder(itemView: View) :ViewHolder(itemView){
    var binding=SubCatItemBinding.bind(itemView)


}