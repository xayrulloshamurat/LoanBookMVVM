package com.example.loan_book_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.loan_book_mvvm.R
import com.example.loan_book_mvvm.databinding.ActivityMainBinding
import com.example.loan_book_mvvm.ui.signIn.SignInFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        if(FirebaseAuth.getInstance().currentUser != null){
            navController.navigate(R.id.action_signInFragment_to_mainFragment)
        }
    }
}