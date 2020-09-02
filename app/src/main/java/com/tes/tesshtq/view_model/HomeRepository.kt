package com.tes.tesshtq.view_model

import android.content.Context
import android.os.AsyncTask
import com.google.gson.JsonObject
import com.tes.tesshtq.model.Data
import com.tes.tesshtq.model.ResponseHome
import com.tes.tesshtq.room.DAO
import com.tes.tesshtq.utils.RestAdapter
import com.tes.tesshtq.utils.RestApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomeRepository(dao: DAO,val context: Context?) {
    private var dao: DAO = dao


    fun getDataHome(
        onResult: (ArrayList<Data<ResponseHome>>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val service: RestApi? = RestAdapter(context!!).callApi()
        service?.getProdukHome()
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { data ->
                    onResult(data)
                },
                { error ->
                    onError(error)
                })
    }


    fun insertProduk(produkModel: ResponseHome.Produk) {
        InsertProdukAsyncTask(dao).execute(produkModel)
    }


    fun getAllProduk(): ArrayList<ResponseHome.Produk> {
        val AllTrendingAsyncTask = GetAllProdukAsyncTask(
            dao
        ).execute()

        return AllTrendingAsyncTask.get()
    }

    private class InsertProdukAsyncTask(val dao: DAO) : AsyncTask<ResponseHome.Produk, Unit, Unit>() {

        override fun doInBackground(vararg produk: ResponseHome.Produk?) {
            dao.insertProduk(produk)
        }
    }

    private inner class GetAllProdukAsyncTask(val dao: DAO) :
        AsyncTask<Unit, Unit, ArrayList<ResponseHome.Produk>>() {
        override fun doInBackground(vararg p0: Unit?): ArrayList<ResponseHome.Produk>? {
            return ArrayList(dao.getAllProduk())
        }
    }



}