package com.project.shope2you.SignUp

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.shope2you.R
import com.project.shope2you.databinding.SignupBinding
import com.project.shope2you.factory.Factory
import com.project.shope2you.viewModel.AuthViewModel


class Signup : Fragment() {
    private lateinit var binding: SignupBinding

    lateinit var mainViewModel: AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= SignupBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        mainViewModel=ViewModelProvider(this,Factory(requireContext())).get(AuthViewModel::class.java)
        binding.apply {

            btnSignUpSignIn.setOnClickListener {
                findNavController().navigate(R.id.login)
            }

            btnSignUpSignUp.setOnClickListener {


                if (check()){
                    mainViewModel.SignUp(binding.signUpEmail.text.toString(),binding.SignUpPassword.text.toString(),
                        binding.signUpName.text.toString(),
                        binding.signUpPhone.text.toString(),
                        binding.signUpAddress.text.toString(),)
                    //createUser(binding.signUpEmail.text.toString(),binding.SignUpPassword.text.toString())




                    findNavController().navigate(R.id.login)


                }

                else{Toast.makeText(context,"input requared",Toast.LENGTH_LONG).show()
                    binding.signUpEmail.text=null
                    binding.SignUpPassword.text=null
                    binding.signUpName.text=null
                    binding.signUpPhone.text=null
                    binding.signUpAddress.text=null
                }
            }


        }


        return binding.root
    }


    private fun check():Boolean{
        var email=binding.signUpEmail.text.toString()
        if(binding.signUpEmail.text.toString()==null){
            binding.signUpEmail.error="this is requird field"
            return false
        }
        if(binding.SignUpPassword.text.toString()==null){
            binding.signUpEmail.error="this is requird field"
            return false
        }
        if(binding.SignUpPassword.text.toString().length<6){
            binding.signUpEmail.error="password minimum 6 char"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.signUpEmail.error="this is requird field"
            return false

        }


        return true
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}











