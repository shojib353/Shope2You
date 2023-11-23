package com.project.shope2you.LogIn

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.shope2you.MainActivity
import com.project.shope2you.R
import com.project.shope2you.databinding.LoginBinding

class Login : Fragment() {
    private lateinit var binding: LoginBinding
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {




        val auth:FirebaseAuth=Firebase.auth
        mainActivity=requireActivity() as MainActivity

        binding=LoginBinding.inflate(layoutInflater)
        binding.apply {


            btnSignInSignUp.setOnClickListener {
                findNavController().navigate(R.id.signup2)
            }
            btnSignIn.setOnClickListener {
                val email=binding.signInEmail.text.toString()
                val password=binding.signInPassword.text.toString()

                if(check()){

                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {


                            mainActivity.hasCompletedWelcome=true
                            mainActivity.restart()

                            Toast.makeText(requireContext(),"sign In",Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener {
                        Toast.makeText(requireContext(),"not sign In",Toast.LENGTH_SHORT).show()
                    }

                }


            }
        }



        return binding.root
    }

    private fun check():Boolean{
        val email=binding.signInEmail.text.toString()
        if(binding.signInEmail.text.toString()==null){
            binding.signInEmail.error="this is requird field"
            return false
        }
        if(binding.signInPassword.text.toString()==null){
            binding.signInEmail.error="this is requird field"
            return false
        }
        if(binding.signInPassword.text.toString().length<6){
            binding.signInEmail.error="password minimum 6 char"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.signInEmail.error="this is requird field"
            return false

        }


        return true
    }
}