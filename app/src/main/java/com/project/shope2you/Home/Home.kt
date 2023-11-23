package com.project.shope2you.Home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.project.shope2you.Home.Adaptar.HomeProductAdaptar
import com.project.shope2you.Home.Adaptar.ProductCatAdaptar
import com.project.shope2you.Home.DataClass.catdata
import com.project.shope2you.Home.DataClass.productmodel
import com.project.shope2you.Product.ProductAdaptar
import com.project.shope2you.R
import com.project.shope2you.databinding.HomeBinding
import kotlin.system.exitProcess


class Home : Fragment() {
    private lateinit var binding: HomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=HomeBinding.inflate(layoutInflater)

        getData()
        getProducts()

        binding.cart.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_cart2)
        }
        return binding.root
    }
    private fun getData() {
        val list =ArrayList<catdata>()
        Firebase.firestore.collection("catagory")
            .get().addOnSuccessListener {

                list.clear()
                for (x in it.documents){
                    val data=x.toObject(catdata::class.java)
                    list.add(data!!)
                }
                binding.catagoryRecycler.layoutManager= GridLayoutManager(requireContext(),3)
                val adapter=ProductCatAdaptar(requireContext(),list)
                binding.catagoryRecycler.adapter=adapter






                    }



            }

    private fun getProducts() {

        val list =ArrayList<productmodel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {

                list.clear()
                for (doc in it.documents){
                    val data=doc.toObject(productmodel::class.java)
                    list.add(data!!)
                }


                binding.HomeproductRecycler.layoutManager= LinearLayoutManager(requireContext())
                val adapter= HomeProductAdaptar(requireContext(),list)
                binding.HomeproductRecycler.adapter=adapter
            }

    }

}