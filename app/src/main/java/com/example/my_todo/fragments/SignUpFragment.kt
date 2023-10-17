package com.example.my_todo.fragments

import android.os.Bundle
import android.service.controls.Control
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.my_todo.R
import com.example.my_todo.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
     private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignUpBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }
     private fun init(view: View){
         navController = Navigation.findNavController(view)
         auth = FirebaseAuth.getInstance()
     }
     private  fun  registerEvents(){

         binding.authtextView.setOnClickListener{
            navController.navigate(R.id.action_signUpFragment_to_signInFragment2)
         }


         binding.nextBtn.setOnClickListener{
            val email = binding.emailEt.text.toString().trim()
             val pass = binding.passwordEt.text.toString().trim()
             val verifyPass = binding.repassET.text.toString().trim()

             if (email.isNotEmpty() && pass.isNotEmpty() && verifyPass.isNotEmpty()){
                 if (pass == verifyPass){

                auth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(
                    {
                        if (it.isSuccessful){
                            Toast.makeText(context ,"Register Successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate(R.id.action_signUpFragment_to_homeFragment3)
                        }else{
                            Toast.makeText(context ,it.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    })
                 }
             }


         }
     }


}