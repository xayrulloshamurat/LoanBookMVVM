package com.example.loan_book_mvvm.ui.signIn

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.FragmentSignInBinding
import com.example.loan_book_mvvm.ui.authHelper.AuthHelperSignIn
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        var authHelper = AuthHelperSignIn()
        binding = FragmentSignInBinding.bind(view)
        binding.signUp.setText(Html.fromHtml("<b> Sign up <b>"))
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.button.setOnClickListener {
            if (binding.hintEmail.text.isEmpty() || binding.hintPassword.text.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Iltimos, qatorlarni to'ldiring!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var email: String = binding.hintEmail.text.toString()
                var password: String = binding.hintPassword.text.toString()
                authHelper.signIn(email,password, AuthResult = {
                    Toast.makeText(requireContext(), "Succesfully Login :)", Toast.LENGTH_SHORT).show()
                }, Exception = {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                } )


//                auth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(requireActivity(), OnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            Toast.makeText(requireContext(), "Succesfully logged in :)", Toast.LENGTH_SHORT).show()
//                        }else{
//                            Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
//                        }
//                    })
            }
        }
    }
}