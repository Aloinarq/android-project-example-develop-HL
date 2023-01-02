package com.levente.project_retrofit.api.model

import com.google.gson.annotations.SerializedName

data class DepartmentResponse(
    @SerializedName("ID")
    var ID : Int,

    @SerializedName("name")
    var name : String

)
