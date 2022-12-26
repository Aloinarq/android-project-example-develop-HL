package com.levente.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName("email")
    var email: String,
    @SerializedName("passwordKey")
    var password: String
)