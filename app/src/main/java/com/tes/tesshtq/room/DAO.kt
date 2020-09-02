package com.tes.tesshtq.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tes.tesshtq.home.ResponseHome


@Dao
interface DAO {

    @Insert
    fun insertProduk(prodakModel: Array<out ResponseHome.Produk?>)

    @Query("SELECT * FROM Produk")
    fun getAllProduk(): List<ResponseHome.Produk>


}