package com.example.keepcodingdragonball.presentation.login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.keepcodingdragonball.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject


class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        setObservers()
        setListeners()

        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    private fun setObservers() {
        viewModel.loginUiState.observe(this.viewLifecycleOwner, this::handleUiState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.getCredentials()
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
                )

            }

            cbRememberPassword.setOnCheckedChangeListener { _, b ->
                if(!b) viewModel.removeCredentials()
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
                goToHeroList()
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

    private fun goToHeroList() {
        val directions = LoginFragmentDirections.navigateToHeroList()
        findNavController().navigate(directions)
    }

    private fun makeToast(message: String) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

    private fun showLoading(isLoading:Boolean){
        with(binding){
            lyContent.visibility = if(isLoading) View.GONE else View.VISIBLE
            pbLogin.visibility = if(isLoading) View.VISIBLE else View.GONE
        }
    }
}