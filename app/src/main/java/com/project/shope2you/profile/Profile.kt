package com.project.shope2you.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.shope2you.MainActivity
import com.project.shope2you.R
import com.project.shope2you.databinding.LoginBinding
import com.project.shope2you.databinding.ProfileBinding


class Profile : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var binding:ProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= ProfileBinding.inflate(layoutInflater)
        mainActivity=requireActivity() as MainActivity

        binding.logout.setOnClickListener {
            mainActivity.hasCompletedWelcome=false
            mainActivity.restart()
        }
        return binding.root
    }


}