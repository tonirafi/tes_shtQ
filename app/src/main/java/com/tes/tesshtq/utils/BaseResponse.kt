package com.tes.tesshtq.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("code")
    @Expose
    var code: Int? = 0

    @SerializedName("token")
    @Expose
    val token: String? = ""

    @SerializedName("massage")
    @Expose
    var message: String? = ""

    @SerializedName("data")
    @Expose
    var data: T? = null

}