package com.example.wisproapi.repositories

import android.content.Context
import com.example.wisproapi.retrofit_models.ClientLogin

class LocalRepository(context: Context) {

    val prefs = context.getSharedPreferences("client_information", Context.MODE_PRIVATE)
    val activeUser: Boolean? = null
    val clientLogin: ClientLogin = ClientLogin()

    fun requireUser() {
        clientLogin.email = prefs.getString("email", "")
        clientLogin.id = prefs.getString("id", "")
        clientLogin.uid = prefs.getString("uid", "")
        clientLogin.access_token = prefs.getString("access_token", "")
        clientLogin.client = prefs.getString("client", "")

    }

    fun isActive(): Boolean {
        requireUser()
        return !(clientLogin.client.isNullOrEmpty() || clientLogin.email.isNullOrEmpty() || clientLogin.id.isNullOrEmpty() || clientLogin.uid.isNullOrEmpty() || clientLogin.access_token.isNullOrEmpty())
    }
}