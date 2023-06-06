package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener(this)

        // atribuição da viewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setObserver()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            viewModel.onLogin(email, password)
        }
    }

    private fun setObserver(){
        // Acessando os valores da viewModel
        viewModel.welcome().observe(this) {
            binding.textWelcome.text = it
        }

        viewModel.login().observe(this) {
            if (it) {
                Toast.makeText(this, "Sucesso!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Falha!", Toast.LENGTH_LONG).show()
            }
        }
    }

}