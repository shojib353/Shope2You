package com.project.shope2you.Repository

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.shope2you.authData.AuthData

class repository(val context:Context) {


    val auth: FirebaseAuth = FirebaseAuth.getInstance()


    fun createUser(email: String, pass: String) {


        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task: Task<SignInMethodQueryResult> ->
                if (task.isSuccessful) {
                    val signInMethods =
                        task.result.signInMethods
                    if (signInMethods != null && !signInMethods.isEmpty()) {
                        // Email already exists

                        Toast.makeText(context, "already resister", Toast.LENGTH_LONG).show()
                        // Handle accordingly (e.g., show an error message)
                    } else {
                        // Email does not exist
                        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {

                            Toast.makeText(context, "SignUp Success", Toast.LENGTH_LONG).show()
 }
                        // Proceed with registration or login
                    }
                } else {
                    // An error occurred
                    // Handle the error
                }
            }


    }

    @SuppressLint("SuspiciousIndentation")
    fun StoreInformation(list: AuthData){
        var db = Firebase.firestore

            db.collection("userInfo").document(list.email).set(list)





    }
}

