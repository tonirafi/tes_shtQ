package com.tes.tesshtq.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data<T> {
    @SerializedName("data")
    @Expose
    var data: T? = null

}