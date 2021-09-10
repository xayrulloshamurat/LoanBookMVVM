package com.example.loan_book_mvvm.ui.signUp

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.FragmentSignUpBinding
import com.example.loan_book_mvvm.ui.authHelper.AuthHelperSignUp
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment() : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        var authHelper = AuthHelperSignUp()
        binding.register.setText(Html.fromHtml("<b> Sign In <b>"))
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.button.setOnClickListener {
            if (binding.emailText.text.isEmpty() || binding.passwordText.text.isEmpty() || binding.confPasswordText.text.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Iltimos qatorlarni to'ldiring",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var email: String = binding.emailText.text.toString()
                var password: String = binding.passwordText.text.toString()
                authHelper.createUser(email, password, AuthResult = {
                    Toast.makeText(requireContext(), "Succesfully registration", Toast.LENGTH_SHORT)
                        .show()
                }, Exception = {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                })
//                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(requireActivity(),
//                    OnCompleteListener { task ->
//                        if(task.isSuccessful){
//                            Toast.makeText(requireContext(), "Succesfully Registered", Toast.LENGTH_SHORT).show()
//                        }else{
//                            Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
//                        }
//                    })
            }
        }
    }
}