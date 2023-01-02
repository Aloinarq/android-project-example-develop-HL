package com.levente.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequestBody(

    @SerializedName("lastName")
    var lastName: String,

    @SerializedName("firstName")
    var firstName: String,

    @SerializedName("location")
    var location: String,

    @SerializedName("phoneNumber")
    var phoneNumber: String,

    @SerializedName("imageUrl")
    var imageUrk: String,

)
