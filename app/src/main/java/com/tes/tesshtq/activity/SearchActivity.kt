package com.tes.tesshtq.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.tes.tesshtq.R
import com.tes.tesshtq.view_model.ProdukViewModel
import com.tes.tesshtq.adapter.ListProdukLocalAdapter
import com.tes.tesshtq.model.ProdakSearch
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.img_back
import kotlinx.android.synthetic.main.activity_search.recyclerView
import kotlinx.android.synthetic.main.activity_search.swipe_refresh_layout
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: ProdukViewModel
    internal lateinit var adapter: ListProdukLocalAdapter


    val param = JsonObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUi()
        setLiveData()
    }



    fun setUi() {
        viewModel = ProdukViewModel(application)
        adapter = ListProdukLocalAdapter(this)
        setData()
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        img_back.setOnClickListener {
            finish()
        }

        swipe_refresh_layout.setOnRefreshListener {
            viewModel.getListProduk("xxxx")

        }
        ed_search.addTextChangedListener(
            object : TextWatcher {

                private var timer = Timer()
                private val DELAY: Long = 500 // milliseconds
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(
                        object : TimerTask() {
                            override fun run() {

                                var data=ed_search.text.toString()

                                if (data.equals("")){
                                    data="xxxx"
                                }
                                viewModel.getListProduk(data)
                            }
                        },
                        DELAY
                    )
                }
            }
        )



    }


    fun setLiveData() {
        viewModel.liveDataProdukLocal.observe(this, Observer {
            adapter.clear()

            if (it?.size != 0 ) {
                var listProdak=it
                adapter.addAll(listProdak!!)

            } else {

            }

            swipe_refresh_layout.isRefreshing = false


        })


    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteAllProduk()
    }




fun setData(){

    var list=ArrayList<ProdakSearch>()
    var p1=ProdakSearch()
    p1.title="Baju Merah"
    p1.description="Baju Merah Dari Kampung Baru"
    p1.imageUrl="https://ecs7.tokopedia.net/img/cache/700/product-1/2019/5/13/59711793/59711793_5613cfe5-f6ff-4113-9c72-92a831301d29_700_700.jpg"
    p1.price="$10"
    p1.loved=1
    list.add(p1)

    var p2=ProdakSearch()
    p2.title="Baju Biru"
    p2.description="Baju Biru Dari Kampung Baru"
    p2.imageUrl="https://ds393qgzrxwzn.cloudfront.net/resize/m500x500/cat1/img/images/0/0BE77hAUBw.jpg"
    p2.price="$10"
    p2.loved=1
    list.add(p2)

    var p3=ProdakSearch()
    p3.title="Baju Kuning"
    p3.description="Baju Kuning Dari Kampung Baru"
    p3.imageUrl="https://ecs7.tokopedia.net/img/cache/700/product-1/2018/10/12/41771692/41771692_719400ef-b395-48fc-bdc0-fc51e32881fd_385_385.jpg"
    p3.price="$13"
    p3.loved=1

    list.add(p3)
    var p4=ProdakSearch()
    p4.title="Baju Abu"
    p4.description="Baju Abu Dari Kampung Baru"
    p4.imageUrl="https://ecs7.tokopedia.net/img/cache/700/product-1/2019/1/12/7790862/7790862_969fe69a-8bd6-448f-a9cd-43983af4ac14_800_800.png"
    p4.price="$15"
    p4.loved=1


    var p5=ProdakSearch()
    p5.title="Baju Coklat"
    p5.description="Baju Coklat Dari Kampung Baru"
    p5.imageUrl="https://ecs7.tokopedia.net/img/cache/700/product-1/2018/11/29/2795198/2795198_dc6faea4-8cdf-476e-adf6-249c4a60992f_1560_1560.jpg"
    p5.price="$8"
    p5.loved=1

    list.forEachIndexed { index, prodakSearch ->
        viewModel.insertProduk(prodakSearch)
    }

}

















}
