package com.example.songabiz.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.songabiz.data.models.AuthModelLogin
import com.example.songabiz.data.models.LoginModel
import com.example.songabiz.data.models.RegisterModel
import com.example.songabiz.presentation.screens.auth.AuthenticationAPI
import com.example.songabiz.presentation.screens.auth.AuthenticationEvent
import com.example.songabiz.presentation.screens.auth.AuthenticationMode
import com.example.songabiz.presentation.screens.auth.AuthenticationState
import com.example.songabiz.presentation.screens.auth.PasswordRequirements
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AuthenticationViewModel : ViewModel() {
    val uiState = MutableStateFlow(AuthenticationState())

    private fun toggleAuthenticationMode() {
        val authenticationMode = uiState.value.authenticationMode
        val newAuthenticationMode = if (
            authenticationMode == AuthenticationMode.SIGN_IN
        ) {
            AuthenticationMode.SIGN_UP
        } else {
            AuthenticationMode.SIGN_IN
        }
        uiState.value = uiState.value.copy(
            authenticationMode = newAuthenticationMode
        )
    }

    private fun updateFirstName(fname: String) {
        uiState.value = uiState.value.copy(
            fname = fname
        )
    }

    private fun updateLastName(sname: String) {
        uiState.value = uiState.value.copy(
            sname = sname
        )
    }

    private fun updatePhone(phone: String) {
        uiState.value = uiState.value.copy(
            phone = phone
        )
    }

    private fun updateEmailAddress(email: String){
        uiState.value = uiState.value.copy(
            email = email
        )
    }

    private fun updatePassword(password: String) {
        val requirements = mutableListOf<PasswordRequirements>()
        if (password.length > 7) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }
        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }
        uiState.value = uiState.value.copy(
            password = password,
            passwordRequirements = requirements.toList()
        )
    }

    private fun authenticate() {
        val authenticationMode = uiState.value.authenticationMode
        if (!uiState.value.isLoading) {
            uiState.value = uiState.value.copy(
                isLoading = true
            )
            if (authenticationMode == AuthenticationMode.SIGN_IN) {
                authLoginRequest()
            } else {
                authRegisterRequest()
            }
        }
    }

    private fun dismissError() {
        uiState.value = uiState.value.copy(
            error = null
        )
    }

    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            is AuthenticationEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }

            is AuthenticationEvent.FirstNameChanged -> {
                updateFirstName(authenticationEvent.fname)
            }

            is AuthenticationEvent.LastNameChanged -> {
                updateLastName(authenticationEvent.sname)
            }

            is AuthenticationEvent.PhoneChanged -> {
                updatePhone(authenticationEvent.phone)
            }

            is AuthenticationEvent.PasswordChanged -> {
                updatePassword(authenticationEvent.password)
            }

            is AuthenticationEvent.EmailChanged -> {
                updateEmailAddress(authenticationEvent.email)
            }

            is AuthenticationEvent.Authenticate -> {
                authenticate()
            }

            is AuthenticationEvent.ErrorDismissed -> {
                dismissError()
            }
        }
    }


    private fun authLoginRequest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://songa-api.onrender.com/api/users/auth/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(AuthenticationAPI::class.java)

        val dataModel = LoginModel(email = uiState.value.email, password = uiState.value.password)

        val call: Call<AuthModelLogin?>? = api.login(dataModel)

        call!!.enqueue(object: Callback<AuthModelLogin?>{
            override fun onResponse(
                call: Call<AuthModelLogin?>,
                response: Response<AuthModelLogin?>
            ) {
//                Request Headers
                val requestHeaders = call.request().headers.toString()
                Log.d("Main", "Response Payload: $requestHeaders")

//                Log Response Headers
                val responseHeaders = call.request().headers.toString()
                Log.d("Main", "Response Payload: $responseHeaders" )

                val responseBody = response.body().toString()
                Log.d("Main", "Response Body: $responseBody")

                if (response.isSuccessful){
                    val model: AuthModelLogin? = response.body()
                    Log.d("Main", "--------> success! " + response.body().toString())
                    Log.d("Main", "--------> success Auth! " + model.toString())
                    Log.d("Main", "--------> success Auth Message! " + model.toString())

                    val msg = model!!.message
                    val user = model!!.user

                    if (uiState.value.isLoading){
                        uiState.value = uiState.value.copy(
                            isLoading = false
                        )
                    }
                    if (msg == "login successfull, using old token"){
                        uiState.value = uiState.value.copy(
                            isSignedIn = true,
                            fname = user!!.fname,
                            sname = user!!.sname,
                            phone = user!!.phone,
                            email = user.email,
                            avatar = user!!.avatar,
                            id = user!!.id,
                            address = user!!.address,
                            sessionToken = user!!.sessionToken
                        )
                        Log.d("Main", "-------> success Auth Session!" + uiState.value.sessionToken)
                    }
                    else {
                        uiState.value = uiState.value.copy(
                            error = "Incorrect email/password"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<AuthModelLogin?>, t: Throwable) {
                Log.e("Main", "--------> Failed Fellow!!" + t.message.toString())
                if (uiState.value.isLoading){
                    uiState.value = uiState.value.copy(
                        isLoading = true
                    )
                }
            }
        })
    }

    private fun authRegisterRequest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://songa-api.onrender.com/api/users/auth/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(AuthenticationAPI::class.java)

        val dataModel = RegisterModel(fname = uiState.value.fname, sname = uiState.value.sname,
            phone = uiState.value.phone, password = uiState.value.password)
        val call: Call<AuthModelLogin?>? = api.registerUser(dataModel)

        call!!.enqueue(object : Callback<AuthModelLogin?> {
            override fun onResponse(
                call: Call<AuthModelLogin?>,
                response: Response<AuthModelLogin?>
            ) {
                val requestHeaders = call.request().headers.toString()
                Log.d("Main","Request Headers: $requestHeaders")

//                Log request payload
                val requestBody = call.request().body?.toString() ?:""
                Log.d("Main", "Request Payload: $requestBody")

                if (response.isSuccessful){
                    val model: AuthModelLogin?= response.body()
                    Log.d("Main", "------> success! " +response.body().toString())
                    Log.d("Main", "--------> success Auth! " + model.toString())
                    Log.d("Main", "--------> success Auth Message! " + model.toString())

                    val msg = model!!.message
                    val user = model!!.user

                    if (uiState.value.isLoading){
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                        )
                    }
                    if (msg == "login successful, using old token"){
                        uiState.value = uiState.value.copy(
                            isSignedIn = true,
                            fname = user!!.fname,
                            sname = user!!.sname,
                            phone = user!!.phone,
                            email = user!!.email,
                            avatar = user!!.avatar,
                            id = user!!.id,
                            address = user!!.address,
                            sessionToken = user!!.sessionToken
                        )
                        Log.d("Main", "-------> success Auth Session! " + uiState.value.sessionToken)
                    }
                    else {
                        uiState.value = uiState.value.copy(
                            error = "Incorrect email/password"
                        )
                    }
                }
            }

            override fun onFailure(call: Call<AuthModelLogin?>, t: Throwable) {
                Log.e("Main", "---------> Failed Fellow!!" + t.message.toString())
                if (uiState.value.isLoading) {
                    uiState.value = uiState.value.copy(
                        isLoading = false
                    )
                }
            }
        })

    }
}