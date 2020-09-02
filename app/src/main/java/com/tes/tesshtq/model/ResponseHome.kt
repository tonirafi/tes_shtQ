package com.tes.tesshtq.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
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

    @Entity(tableName = "Produk")
    class Produk() : Parcelable {

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

        constructor(parcel: Parcel) : this() {
            id = parcel.readValue(Int::class.java.classLoader) as? Int
            imageUrl = parcel.readString()
            title = parcel.readString()
            description = parcel.readString()
            price = parcel.readString()
            loved = parcel.readValue(Int::class.java.classLoader) as? Int
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeValue(id)
            parcel.writeString(imageUrl)
            parcel.writeString(title)
            parcel.writeString(description)
            parcel.writeString(price)
            parcel.writeValue(loved)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Produk> {
            override fun createFromParcel(parcel: Parcel): Produk {
                return Produk(parcel)
            }

            override fun newArray(size: Int): Array<Produk?> {
                return arrayOfNulls(size)
            }
        }


    }
}