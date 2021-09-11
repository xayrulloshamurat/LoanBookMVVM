package com.example.loan_book_mvvm.ui.signIn

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.FragmentSignInBinding
import com.example.loan_book_mvvm.ui.authHelper.AuthHelperSignIn
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                viewModel.signIn(email, password)
                viewModel.signIn.observe(viewLifecycleOwner,{
                    when(it){
                        "loading"->{
                            binding.progressBar.isVisible = true
                        }
                        "success"->{
                            binding.progressBar.isVisible = false
                            Toast.makeText(requireContext(), "Succesfully Login", Toast.LENGTH_SHORT).show()
                        }
                        it->{
                            binding.progressBar.isVisible = false
                            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}