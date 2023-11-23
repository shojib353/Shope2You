package com.project.shope2you.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.project.shope2you.Repository.repository
import com.project.shope2you.authData.AuthData

class AuthViewModel(val context: Context):ViewModel(){
    var email:String=""
    var pass:String=""
    var name:String=""
    var phone:String=""
    var address:String=""






    fun SignUp(email:String,pass:String){
        this.email=email
        this.pass=pass
        repository(context).createUser(email,pass)



    }

    fun Information(toString: String, toString1: String, toString2: String) {



        val data=AuthData(toString,toString1,toString2,email)
        repository(context).StoreInformation(data)



    }



}