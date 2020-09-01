package com.tes.tesshtq.utils


import com.google.gson.JsonObject
import com.tes.tesshtq.home.ResponseHome
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("home")
    fun getProdukHome(
        @Body data: JsonObject
    ): Observable<BaseResponse<ResponseHome>>

}
