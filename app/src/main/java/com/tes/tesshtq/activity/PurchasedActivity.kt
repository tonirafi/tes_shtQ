package com.tes.tesshtq.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.tes.tesshtq.R
import com.tes.tesshtq.view_model.ProdukViewModel
import com.tes.tesshtq.adapter.ListProdukPurchasedAdapter
import kotlinx.android.synthetic.main.activity_purchased.*

class PurchasedActivity : AppCompatActivity() {

    private lateinit var viewModel: ProdukViewModel
    internal lateinit var purchasedAdapter: ListProdukPurchasedAdapter


    val param = JsonObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchased)

        setUi()
        setLiveData()
    }




    fun setUi() {
        viewModel = ProdukViewModel(application)
        purchasedAdapter = ListProdukPurchasedAdapter(this)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = purchasedAdapter

        img_back.setOnClickListener {
            finish()
        }

        swipe_refresh_layout.setOnRefreshListener {
            refresh()
        }

        refresh()

    }


    fun setLiveData() {
        viewModel.liveDataPurchased.observe(this, Observer {



            if (it?.size != 0 ) {
                purchasedAdapter.addAll(it!!)

            } else {

//                Tools.showPesan(it.message, requireContext())
            }

            swipe_refresh_layout.isRefreshing = false


        })


    }


    fun refresh() {
        swipe_refresh_layout.isRefreshing = true
        purchasedAdapter.clear()
        viewModel.getAllPurchased()
    }



















}
