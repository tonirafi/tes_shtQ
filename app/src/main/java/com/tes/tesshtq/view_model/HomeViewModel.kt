package com.tes.tesshtq.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.tes.tesshtq.model.Data
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

    val liveDataHome = MutableLiveData<ArrayList<Data<ResponseHome>>>()
    fun getDataHome(data: JsonObject) {

        produkRepository.getDataHome(data, {
            liveDataHome.postValue(it)
        }, {
//            context?.let { it1 ->
//                Tools.showPesan(it.message.toString(), it1)
//            }
        })

    }

    fun insertProduk(produk: ResponseHome.Produk) {
        produkRepository.insertProduk(produk)
    }

    val liveDataProduk = MutableLiveData<ArrayList<ResponseHome.Produk>>()

    fun getListProduk() {
        liveDataProduk.postValue(produkRepository.getAllProduk())

    }
}