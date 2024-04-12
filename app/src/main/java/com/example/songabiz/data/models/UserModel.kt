package com.example.songabiz.data.models

@Suppress("unused")
data class UserModel(
    var profile: ProfileModel
)

data class ProfileModel(
    var fname: String?,
    var sname: String?,
    var phone: String?,
    var avatar: String?,
    var email: String?,
    var address: String?,
    var id: String?,
    var sessionToken: String?
)

data class AuthModelLogin(
    var message: String? = null,
    var user: ProfileModel? = null
)

data class LoginModel(
    var email: String?,
    var password: String?
)

data class RegisterModel(
    var fname: String?,
    var sname: String?,
    var phone: String?,
    var password: String?
)