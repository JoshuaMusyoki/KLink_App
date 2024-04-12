package com.example.songabiz.data.users

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserData (private val context: Context){
    companion object {
        private val Context.dataStore: DataStore<Preferences>  by preferencesDataStore("userProfile")
        val USER_LOGIN_KEY = stringPreferencesKey("user_login")
        val USER_FIRSTNAME_KEY = stringPreferencesKey("user_first_name")
        val USER_LASTNAME_KEY = stringPreferencesKey("user_last_name")
        val USER_PHONE_KEY = stringPreferencesKey("user_phone")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_AVATAR_KEY = stringPreferencesKey("user_avatar")
        val USER_ADDRESS_KEY = stringPreferencesKey("user_address")
        val USER_ID_KEY = stringPreferencesKey("user_id")
        val USER_SESSION_TOKEN_KEY = stringPreferencesKey("user_session_token")
    }
    val getLogin: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_LOGIN_KEY]?:""
        }
    val getFirstName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_FIRSTNAME_KEY]?:""
        }
    val getLastName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_LASTNAME_KEY]?:""
        }
    val getPhone: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PHONE_KEY] ?: ""
        }

    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    val getAvatar: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_AVATAR_KEY] ?: ""
        }

    val getAddress: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ADDRESS_KEY] ?: ""
        }

    val getUserId: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ID_KEY] ?: ""
        }

    val getSessionToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_SESSION_TOKEN_KEY] ?: ""
        }

    suspend fun saveLogin(name: String){
        context.dataStore.edit {prefernces ->
            prefernces[USER_LOGIN_KEY] = name
        }
    }
    suspend fun saveFirstName(name: String){
        context.dataStore.edit { preferences ->
            preferences[USER_FIRSTNAME_KEY] = name
        }
    }
    suspend fun saveLastName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_LASTNAME_KEY] = name
        }
    }

    suspend fun savePhone(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PHONE_KEY] = name
        }
    }

    suspend fun saveEmail(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = name
        }
    }

    suspend fun saveAvatar(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_AVATAR_KEY] = name
        }
    }

    suspend fun saveAddress(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ADDRESS_KEY] = name
        }
    }

    suspend fun saveUserId(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = name
        }
    }

    suspend fun saveSessionToken(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_SESSION_TOKEN_KEY] = name
        }
    }

    suspend fun deleteAllPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}