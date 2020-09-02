package com.tes.tesshtq.utils


import com.tes.tesshtq.model.ResponseHome
import com.tes.tesshtq.model.Data
import retrofit2.http.GET
import rx.Observable

interface RestApi {

    @GET("home")
    fun getProdukHome(): Observable<ArrayList<Data<ResponseHome>>>

}
