package com.example.wisproapi.retrofit_models

import com.google.gson.annotations.SerializedName

class ClientAccount {
    @SerializedName("data") val data: ClientData? = null
    @SerializedName("errors") val errors: List<String>? = null
}
class ClientData {
    @SerializedName("id") val id: Int? = null
    @SerializedName("email") val email: String? = null
    @SerializedName("provider") val provider: String? = null
    @SerializedName("uid") val uid: String? = null
    @SerializedName("name") val name: String? = null
    @SerializedName("allow_password_change") val allow_password_change: Boolean? = null
}