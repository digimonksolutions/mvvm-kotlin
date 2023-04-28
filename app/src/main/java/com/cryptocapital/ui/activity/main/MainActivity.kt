package com.cryptocapital.ui.activity.main

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.cryptocapital.databinding.ActivityMainBinding
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    private var mOnBackPressedDispatcher: OnBackPressedDispatcher? = null
    private val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // Do something when the back button is pressed
                binding.navHostFragmentContainer.findNavController().navigateUp()
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mOnBackPressedDispatcher = onBackPressedDispatcher;
        mOnBackPressedDispatcher!!.addCallback(this, callback)

    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
    }

}