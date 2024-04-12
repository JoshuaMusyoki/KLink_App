package com.example.songabiz.presentation.screens.auth

data class AuthenticationState (
    val authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN,
    var fname:String? = null,
    var sname:String? = null,
    var email:String? = null,
    var phone:String? = "", //""0757478812",
    val password: String?="",//"manyara5766"
    val passwordRequirements: List<PasswordRequirements> = emptyList(),
    val isLoading: Boolean = false,
    val error:String? = null,
    var isSignedIn: Boolean = false,
    var avatar: String? = null,
    var address:String? = null,
    var id:String? = null,
    var sessionToken: String? = null
){
    fun isFormValid():Boolean{
        if (authenticationMode == AuthenticationMode.SIGN_UP){
            return fname?.isNotEmpty() == true && sname?.isNotEmpty() == true &&
                    password?.isNotEmpty() == true && email?.isNotEmpty() == true &&
                    phone?.isNotEmpty() == true
        }
        else {
            return password?.isNotEmpty() == true && email?.isNotEmpty() ==true &&
                    (authenticationMode == AuthenticationMode.SIGN_IN || passwordRequirements.containsAll(
                        PasswordRequirements.entries.toList()
                    ))
        }
    }

}