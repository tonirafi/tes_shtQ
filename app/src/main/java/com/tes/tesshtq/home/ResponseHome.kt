package com.tes.tesshtq.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseHome {


        @SerializedName("productPromo")
        @Expose
        var productPromo: ArrayList<Produk>? = ArrayList<Produk>()

        @SerializedName("category")
        @Expose
        var category: ArrayList<Category>? = ArrayList<Category>()


    class Category {

        @SerializedName("id")
        @Expose
        var id: String? = ""

        @SerializedName("imageUrl")
        @Expose
        var imageUrl: String? = ""

        @SerializedName("name")
        @Expose
        var name: String? = ""
    }


    class Produk {

        @SerializedName("id")
        @Expose
        var id: String? = ""

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
        var loved: String? = ""

    }
}