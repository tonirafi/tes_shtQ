package com.tes.tesshtq.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "ProdakSearch")

class ProdakSearch {

        @PrimaryKey(autoGenerate = true)
        var id: Int? = null

        @SerializedName("imageUrl")
        @Expose
        var imageUrl: String? = ""

        @SerializedName("title")
        @Expose
        var title: String? = ""

        @SerializedName("description")
        @Expose
        var description: String? = ""

        @SerializedName("price")
        @Expose
        var price: String? = ""

        @SerializedName("loved")
        @Expose
        var loved: Int? = 0


}