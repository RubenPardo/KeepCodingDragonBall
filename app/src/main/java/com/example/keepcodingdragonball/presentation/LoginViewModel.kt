package com.example.keepcodingdragonball.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepcodingdragonball.data.repositories.AuthRepository
import com.example.keepcodingdragonball.data.repositories.AuthRepositoryImpl
import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.domain.model.Response
import com.example.keepcodingdragonball.domain.usecases.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginUiState = MutableLiveData<LoginUiState>(LoginUiState.InitState)
    val loginUiState: LiveData<LoginUiState> = _loginUiState
    private var authRepository: AuthRepository = AuthRepositoryImpl()



    fun login(name:String, password:String,saveCredentials:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            _loginUiState.postValue((LoginUiState.Loading))
            delay(1000)
            val loginDataDO = LoginDataDO(name,password)
            if(checkCredentials(loginDataDO)){
                if (LoginUseCase().invoke(loginDataDO,saveCredentials)){
                    _loginUiState.postValue(LoginUiState.Logged)
                }else{
                    emitError("Unable to save credentials")
                }
            }else{
                emitError("Bad credentials")

            }

        }
    }

    fun removeCredentials(){
        viewModelScope.launch {
            authRepository.removeCredentials()
        }
    }

    fun getCredentials(){
        viewModelScope.launch {

            _loginUiState.value = (LoginUiState.Loading)
            when (val response = authRepository.getCredentials()){
                is Response.Error -> emitError("Unable to get credentials")
                is Response.Success -> {
                    response.data?.
                        let {
                            // data no es null
                            _loginUiState.value = (LoginUiState.AlreadyLogged(response.data))
                        }
                        ?: run {
                            // data es null
                            _loginUiState.value = LoginUiState.InitState
                        }
                }
            }
        }
    }

    private fun emitError(s: String) {
        _loginUiState.postValue(LoginUiState.Error(s))
    }

    fun checkCredentials(loginDataDO: LoginDataDO): Boolean {
        return loginDataDO.name.contains("@") && loginDataDO.name.contains(".") && loginDataDO.password.length > 3
    }



}


sealed class LoginUiState{
    object InitState:LoginUiState()
    object Loading:LoginUiState()
    object Logged:LoginUiState()
    class AlreadyLogged(val loginDataDO: LoginDataDO):LoginUiState()
    class Error(val messageError:String):LoginUiState()
}