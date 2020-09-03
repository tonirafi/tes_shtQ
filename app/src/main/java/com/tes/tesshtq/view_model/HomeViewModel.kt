package com.tes.tesshtq.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tes.tesshtq.model.Data
import com.tes.tesshtq.model.ProdakSearch
import com.tes.tesshtq.model.ResponseHome
import com.tes.tesshtq.room.AppDatabase
import com.tes.tesshtq.room.DAO


class HomeViewModel(val application: Application) : ViewModel() {
    private var produkRepository : HomeRepository
    private var dao: DAO

    init {

        dao = AppDatabase.getDatabase(application).DAO()
        produkRepository =
            HomeRepository(dao, application)

    }

    val liveDataProdak = MutableLiveData<ArrayList<Data<ResponseHome>>>()
    fun getDataProduk() {

        produkRepository.getDataHome( {
            liveDataProdak.postValue(it)
        }, {
//            context?.let { it1 ->
//                Tools.showPesan(it.message.toString(), it1)
//            }
        })

    }

    val liveDataCategory = MutableLiveData<ArrayList<Data<ResponseHome>>>()
    fun getDataCategory() {

        produkRepository.getDataHome( {
            liveDataCategory.postValue(it)
        }, {
//            context?.let { it1 ->
//                Tools.showPesan(it.message.toString(), it1)
//            }
        })

    }

    fun insertPurchased(produk: ResponseHome.Produk) {
        produkRepository.insertPurchased(produk)
    }

    val liveDataPurchased = MutableLiveData<ArrayList<ResponseHome.Produk>>()

    fun getAllPurchased() {
        liveDataPurchased.postValue(produkRepository.getAllPurchased())

    }


    fun insertProduk(produk: ProdakSearch) {
        produkRepository.insertProduk(produk)
    }

    fun deleteAllProduk() {
        produkRepository.deleteAllProdak()
    }

    val liveDataProdukLocal = MutableLiveData<ArrayList<ProdakSearch>>()

    fun getListProduk(title: String) {
        liveDataProdukLocal.postValue(produkRepository.getAllProduk("%$title%"))

    }
}