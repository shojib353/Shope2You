package com.project.shope2you.Product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.shope2you.Home.DataClass.productmodel
import com.project.shope2you.databinding.ProductBinding



class Product : Fragment() {
    private lateinit var binding:ProductBinding
    private val args: ProductArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=ProductBinding.inflate(layoutInflater)
        val text = "${args.name}"
        product(text)


        return binding.root
    }

    private fun product(subcat: String?) {

        val list = ArrayList<productmodel>()
        Firebase.firestore.collection("products").whereEqualTo("productsubCatagory", subcat)
            .get().addOnSuccessListener {

                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(productmodel::class.java)
                    list.add(data!!)
                }
                binding.productRecycler.layoutManager= LinearLayoutManager(requireContext())
                val adapter= ProductAdaptar(requireContext(),list)
                binding.productRecycler.adapter=adapter
            }
    }
}