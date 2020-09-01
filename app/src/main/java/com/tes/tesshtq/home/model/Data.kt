package com.tes.tesshtq.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data<T> {
    @SerializedName("data")
    @Expose
    var data: T? = null

}