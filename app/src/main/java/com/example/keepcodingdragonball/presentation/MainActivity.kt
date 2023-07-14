package com.example.keepcodingdragonball.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.keepcodingdragonball.databinding.ActivityLoginBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        setListeners()

    }

    private fun setObservers() {
        viewModel.loginUiState.observe(this, this::handleUiState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.getCredentials(this)
    }

    private fun setListeners() {

        with (binding){
            etNombre.doAfterTextChanged {
                it?.let{ button.isEnabled = it.toString().isNotEmpty() && etPassword.text.isNotEmpty()}
            }

            etPassword.doAfterTextChanged {
                it?.let{ button.isEnabled = it.toString().isNotEmpty() && etNombre.text.isNotEmpty()}
            }

            button.setOnClickListener{
                viewModel.login(
                    etNombre.text.toString(),
                    etPassword.text.toString(),
                    cbRememberPassword.isChecked,
                    this@MainActivity
                )

            }

            cbRememberPassword.setOnCheckedChangeListener { compoundButton, b ->
                if(!b) viewModel.removeCredentials(this@MainActivity)
            }
        }

    }

    private fun handleUiState(loginUiState: LoginUiState) {
        Log.d("PRUEBA",loginUiState::class.java.name)
       when(loginUiState){

           is LoginUiState.Error -> {
               showLoading(false)
               makeToast(loginUiState.messageError)
           }

           LoginUiState.InitState -> showLoading(false)

           LoginUiState.Loading -> showLoading(true)

           LoginUiState.Logged -> {
               showLoading(false)
               makeToast("Logged successfully")
           }

           is LoginUiState.AlreadyLogged -> {
               showLoading(false)
               with(binding){
                   etPassword.setText(loginUiState.loginDataDO.password)
                   etNombre.setText(loginUiState.loginDataDO.name)
                   cbRememberPassword.isChecked = true
               }
           }
       }
    }

    private fun makeToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    private fun showLoading(isLoading:Boolean){
        with(binding){
            lyContent.visibility = if(isLoading) View.GONE else View.VISIBLE
            pbLogin.visibility = if(isLoading) View.VISIBLE else View.GONE
        }
    }

}