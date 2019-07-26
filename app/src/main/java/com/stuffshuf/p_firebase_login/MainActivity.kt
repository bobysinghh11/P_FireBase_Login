package com.stuffshuf.p_firebase_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()




        if (auth.currentUser != null) {
            button.setOnClickListener {
                auth.signInWithEmailAndPassword(edtvEail.text.toString(), edtvPass.text.toString())
                    .addOnCompleteListener {
                       button.isEnabled = false
                    }.addOnSuccessListener {
                        button.isVisible=false
                        btnsignup.isEnabled=true
                        btnsignup.isVisible=false
                        btnout.isVisible = true
                        btnout.isEnabled = true
                        Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }


            }

        }

            btnsignup.setOnClickListener {
                if (auth.currentUser==null)
                {
                    auth.createUserWithEmailAndPassword(edtvEail.text.toString(), edtvPass.text.toString())
                        .addOnCompleteListener {

                        }.addOnSuccessListener {

                            Toast.makeText(this, "Created Account Successfully", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                        }

                }
        }

        btnout.setOnClickListener {
            auth.signOut()
            btnout.isVisible=false
            btnsignup.isVisible=true
            button.isEnabled=true
            button.isVisible=true
        }
    }
}


