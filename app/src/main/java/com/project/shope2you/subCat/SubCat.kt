package com.project.shope2you.subCat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.shope2you.Home.Adaptar.ProductCatAdaptar
import com.project.shope2you.Home.DataClass.subcatdata

import com.project.shope2you.databinding.SubCatBinding

class SubCat : Fragment() {
    private lateinit var binding:SubCatBinding
    private val args: SubCatArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =SubCatBinding.inflate(layoutInflater)
        //val name = arguments?.getString("name")
        val text = "${args.name}"

        subcatagory(text)






        return binding.root
    }
    private fun subcatagory(Category: String?) {







        val list = ArrayList<subcatdata>()
        Firebase.firestore.collection("subcatagory").whereEqualTo("productCatagory", Category)
            .get().addOnSuccessListener {

                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(subcatdata::class.java)
                    list.add(data!!)
                }


                binding.SubCatagoryRecycler.layoutManager= GridLayoutManager(requireContext(),3)
                val adapter= SubCatAdaptar(requireContext(),list)
                binding.SubCatagoryRecycler.adapter=adapter

            }
    }


}