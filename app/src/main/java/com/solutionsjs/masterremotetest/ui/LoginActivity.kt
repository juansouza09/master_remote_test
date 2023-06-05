package com.solutionsjs.masterremotetest.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.solutionsjs.masterremotetest.databinding.ActivityLoginBinding
import com.solutionsjs.masterremotetest.domain.MasterViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: MasterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login.apply {
            setOnClickListener {
                val username = binding.username.text.toString()
                val password = binding.password.text.toString()
                loginViewModel.login(username, password, this@LoginActivity)
            }
        }
    }
}
