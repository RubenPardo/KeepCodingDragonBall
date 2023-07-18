package com.example.keepcodingdragonball

import com.example.keepcodingdragonball.domain.model.LoginDataDO
import com.example.keepcodingdragonball.presentation.login.LoginViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test


class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    //private lateinit var mockObserver: Observer<LoginUiState> ;

    /*@Before
    private fun setUp(){
       viewModel = LoginViewModel()
        mockObserver = mock(Observer::class.java)
    }*/



    @Test
    fun `test user valid`(){
        with(viewModel){
            assertTrue(checkCredentials(LoginDataDO("@.","1234")))
            assertTrue(checkCredentials(LoginDataDO(".@","1235")))
            assertTrue(checkCredentials(LoginDataDO(".@112","1235")))
            assertFalse(checkCredentials(LoginDataDO(".112","1235")))
            assertFalse(checkCredentials(LoginDataDO("@12","1235")))
            assertFalse(checkCredentials(LoginDataDO("@12","12")))
            assertFalse(checkCredentials(LoginDataDO(".@","12")))
        }

    }

    /*@Test
    suspend fun `test enter app when credentials saved`(){

        val context: Context = mock(Context::class.java)
        val authRepository = mock(AuthRepository::class.java)
        val result = LoginDataDO("Nombre","1234")

        Mockito.`when`(authRepository.getCredentials(context)).thenReturn(Response.Success(result))

        viewModel.getCredentials(context)

        viewModel.loginUiState.observe(this,)
        val firstMock: LoginUiState.InitState = mock(LoginUiState.InitState::class.java)
        val secondMock: LoginUiState.Loading = mock(LoginUiState.Loading::class.java)

        val inOrder = inOrder(firstMock, secondMock)




    }*/

}