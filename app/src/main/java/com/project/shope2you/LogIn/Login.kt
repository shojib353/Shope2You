package com.project.shope2you.LogIn

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.shope2you.MainActivity
import com.project.shope2you.R
import com.project.shope2you.databinding.LoginBinding
import kotlin.system.exitProcess

class Login : Fragment() {
    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth


    private lateinit var binding: LoginBinding
    private lateinit var mainActivity: MainActivity


    private fun onBackPressedMethod() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes"){ _,_ ->

                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(1)
            }
            .setNegativeButton("No",null)
            .create()
            .show()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                onBackPressedMethod()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this, // LifecycleOwner
            callback
        )
    }


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

                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success")
                                val user = auth.currentUser
                                mainActivity.hasCompletedWelcome=true
                                mainActivity.restart()

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                mainActivity.hasCompletedWelcome=false
                                mainActivity.restart()

                            }
                        }

                   /* auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {


                            if(it.isComplete){
                                mainActivity.hasCompletedWelcome=true
                                mainActivity.restart()

                                Toast.makeText(requireContext(),"sign In",Toast.LENGTH_SHORT).show()

                            }
                        else{Toast.makeText(requireContext(),"not sign In",Toast.LENGTH_SHORT).show()}

                    }*/
                }


            }
        }



        return binding.root
    }
    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            mainActivity.hasCompletedWelcome=true
            mainActivity.restart()
        }


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