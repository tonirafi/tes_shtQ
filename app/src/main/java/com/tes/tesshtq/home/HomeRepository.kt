package com.tes.tesshtq.home

import android.content.Context
import com.google.gson.JsonObject
import com.tes.tesshtq.home.model.Data
import com.tes.tesshtq.utils.BaseResponse
import com.tes.tesshtq.utils.RestAdapter
import com.tes.tesshtq.utils.RestApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomeRepository(val context: Context?) {


    fun getDataHome(
        data: JsonObject,
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



}