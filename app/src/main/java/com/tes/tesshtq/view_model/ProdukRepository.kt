package com.tes.tesshtq.view_model

import android.content.Context
import android.os.AsyncTask
import com.tes.tesshtq.model.Data
import com.tes.tesshtq.model.ProdakSearch
import com.tes.tesshtq.model.ResponseHome
import com.tes.tesshtq.room.DAO
import com.tes.tesshtq.utils.RestAdapter
import com.tes.tesshtq.utils.RestApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ProdukRepository(dao: DAO, val context: Context?) {
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


    fun insertPurchased(produkModel: ResponseHome.Produk) {
        insertPurchasedAsyncTask(dao).execute(produkModel)
    }


    fun getAllPurchased(): ArrayList<ResponseHome.Produk> {
        val AllTrendingAsyncTask = getAllPurchased(
            dao
        ).execute()

        return AllTrendingAsyncTask.get()
    }

    private class insertPurchasedAsyncTask(val dao: DAO) : AsyncTask<ResponseHome.Produk, Unit, Unit>() {

        override fun doInBackground(vararg produk: ResponseHome.Produk?) {
            dao.insertPurchased(produk)
        }
    }

    private inner class getAllPurchased(val dao: DAO) :
        AsyncTask<Unit, Unit, ArrayList<ResponseHome.Produk>>() {
        override fun doInBackground(vararg p0: Unit?): ArrayList<ResponseHome.Produk>? {
            return ArrayList(dao.getAllPurchased())
        }
    }


    fun insertProduk(produkModel: ProdakSearch) {
        insertProdukdAsyncTask(dao).execute(produkModel)
    }


    fun getAllProduk(title: String): ArrayList<ProdakSearch> {
        val AllTrendingAsyncTask = getAllProduk(
            dao,title
        ).execute()

        return AllTrendingAsyncTask.get()
    }

    fun deleteAllProdak() {
        val AllTrendingAsyncTask = deleteAllProdak(
            dao
        ).execute()

        return AllTrendingAsyncTask.get()
    }

    private class insertProdukdAsyncTask(val dao: DAO) : AsyncTask<ProdakSearch, Unit, Unit>() {

        override fun doInBackground(vararg produk:ProdakSearch?) {
            dao.insertProdak(produk)
        }
    }

    private inner class getAllProduk(val dao: DAO,val title: String) :
        AsyncTask<Unit, Unit, ArrayList<ProdakSearch>>() {
        override fun doInBackground(vararg p0: Unit?): ArrayList<ProdakSearch>? {
            return ArrayList(dao.getAllProduk(title))
        }
    }

    private inner class deleteAllProdak(val dao: DAO) :
        AsyncTask<ProdakSearch, Unit, Unit>() {
        override fun doInBackground(vararg p0: ProdakSearch?) {
            dao.deleteAllProdak()

        }

    }



}