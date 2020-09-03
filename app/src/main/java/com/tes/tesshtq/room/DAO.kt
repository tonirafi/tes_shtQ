package com.tes.tesshtq.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tes.tesshtq.model.ProdakSearch
import com.tes.tesshtq.model.ResponseHome


@Dao
interface DAO {

    @Insert
    fun insertPurchased(prodakModel: Array<out ResponseHome.Produk?>)

    @Query("SELECT * FROM Produk")
    fun getAllPurchased(): List<ResponseHome.Produk>


    @Insert
    fun insertProdak(prodakModel: Array<out ProdakSearch?>)

    @Query("SELECT * FROM ProdakSearch WHERE title LIKE :search")
    fun getAllProduk(search:String): List<ProdakSearch>

    @Query("DELETE FROM ProdakSearch")
    fun deleteAllProdak()
}