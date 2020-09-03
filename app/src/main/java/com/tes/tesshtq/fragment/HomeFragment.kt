package com.tes.tesshtq.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import com.tes.tesshtq.R
import com.tes.tesshtq.activity.SearchActivity
import com.tes.tesshtq.adapter.ListProdukHomeAdapter
import com.tes.tesshtq.view_model.ProdukViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: ProdukViewModel
    internal lateinit var adapter: ListProdukHomeAdapter
    private lateinit var dialog: ProgressDialog
    private var isfirst=true



    val param = JsonObject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ProdukViewModel(requireActivity().application)

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setUi()
        setLiveData()
    }


    fun setUi() {

        adapter = ListProdukHomeAdapter(requireContext())

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        swipe_refresh_layout.setOnRefreshListener {
            getProduk()
        }

        lnrl_search.setOnClickListener {
           var inten=Intent(requireContext(),
               SearchActivity::class.java)
            startActivity(inten)

        }

        getCategory()
        
        tabCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

                    getProduk()
            }

        })

        isfirst=false

    }


    fun setLiveData() {


        viewModel.liveDataCategory.observe(viewLifecycleOwner, Observer {
            swipe_refresh_layout.isRefreshing = false

            if (it?.size != 0 ) {
                var listCategory= it[0].data?.category
                listCategory?.forEachIndexed { index, category ->

                    tabCategory.addTab(tabCategory.newTab())
                    tabCategory.getTabAt(index)?.setCustomView(R.layout.tab_category)
                    tabCategory.getTabAt(index)?.customView?.findViewById<TextView>(R.id.tv_title)?.text=category.name
                    Glide.with(requireActivity()).load(category.imageUrl).into(tabCategory.getTabAt(index)?.customView?.findViewById(R.id.img_category)!!)

                }
            } else {

//                Tools.showPesan(it.message, requireContext())
            }



        })

        viewModel.liveDataProdak.observe(viewLifecycleOwner, Observer {

            if (it?.size != 0 ) {

                var listProdak=it[0].data?.productPromo
                adapter.addAll(listProdak!!)

            } else {

//                Tools.showPesan(it.message, requireContext())
            }

            swipe_refresh_layout.isRefreshing = false


        })



    }


    fun getProduk() {
        swipe_refresh_layout.isRefreshing = true
        adapter.clear()
        viewModel.getDataProduk()
    }

    fun getCategory(){
        swipe_refresh_layout.isRefreshing = true

        tabCategory.removeAllTabs()
        viewModel.getDataCategory()

    }


















}
