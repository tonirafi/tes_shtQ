package com.tes.tesshtq.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.tes.tesshtq.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var ViewModel: HomeViewModel
    internal lateinit var adapter: ListProdukAdapter


    val param = JsonObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUi()
        setLiveData()
    }



    fun setUi() {
        ViewModel = HomeViewModel(application)
        adapter = ListProdukAdapter(this)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        img_back.setOnClickListener {
            finish()
        }

        swipe_refresh_layout.setOnRefreshListener {
            refresh()
        }

        refresh()

    }


    fun setLiveData() {
        ViewModel.liveDataHome.observe(this, Observer {



            if (it?.size != 0 ) {

                var listProdak=it[0].data?.productPromo
                adapter.addAll(listProdak!!)

            } else {

//                Tools.showPesan(it.message, requireContext())
            }

            swipe_refresh_layout.isRefreshing = false


        })


    }


    fun refresh() {
        swipe_refresh_layout.isRefreshing = true
        adapter.clear()
        ViewModel.getDataHome(param)
    }



















}