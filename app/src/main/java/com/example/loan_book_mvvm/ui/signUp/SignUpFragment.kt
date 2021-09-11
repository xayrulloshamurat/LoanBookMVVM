package com.example.loan_book_mvvm.ui.signUp

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.FragmentSignUpBinding
import com.example.loan_book_mvvm.ui.authHelper.AuthHelperSignUp
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment() : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
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
            } else if (binding.passwordText.text.toString() != binding.confPasswordText.text.toString()) {
                Toast.makeText(
                    requireContext(),
                    "Parollaringiz muvofiqligini tekshiring!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                var email: String = binding.emailText.text.toString()
                var password: String = binding.passwordText.text.toString()
                viewModel.signUp(email, password)
                viewModel.signUp.observe(viewLifecycleOwner, {
                    when (it) {
                        "loading" -> {
                            binding.progressBar.isVisible = true
                        }
                        "success" -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(
                                requireContext(),
                                "Successfully registration",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        it -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}