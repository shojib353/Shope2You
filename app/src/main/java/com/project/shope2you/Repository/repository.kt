package com.project.shope2you.Repository

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.shope2you.authData.AuthData

class repository(val context:Context) {


    val auth: FirebaseAuth = FirebaseAuth.getInstance()


    fun createUser(email: String, pass: String,list: AuthData) {


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
                        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                val user = auth.currentUser
                                Firebase.firestore.collection("userInfo").document(list.email).set(list)
                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)

                                //updateUI(null)
                            }
                        }
                    }
                }


            }

        @SuppressLint("SuspiciousIndentation")
        fun StoreInformation(list: AuthData){









        }
    }
}

