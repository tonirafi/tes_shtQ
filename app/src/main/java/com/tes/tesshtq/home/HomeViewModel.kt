package com.tes.tesshtq.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.tes.tesshtq.home.model.Data
import com.tes.tesshtq.utils.BaseResponse


class HomeViewModel(val context: Context?) : ViewModel() {
    private var produkRepository = HomeRepository(context)



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
}