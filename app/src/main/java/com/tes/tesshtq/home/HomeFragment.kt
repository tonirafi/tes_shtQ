package com.tes.tesshtq.home

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import com.tes.tesshtq.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var ViewModel: HomeViewModel
    internal lateinit var adapter: ListProdukAdapter
    private lateinit var dialog: ProgressDialog


    val param = JsonObject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ViewModel = HomeViewModel(requireContext())

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setUi()
        setLiveData()
    }


    fun setUi() {
        tabCategory.newTab()?.setCustomView(R.layout.tab_category)
        tabCategory.newTab()?.customView?.findViewById<TextView>(R.id.tv_title)?.text="category.name"

        adapter = ListProdukAdapter(requireContext())

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        swipe_refresh_layout.setOnRefreshListener {
            refresh()
        }

        refresh()

        tabCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
//                param.addProperty("statusTab", p0?.tag.toString().toInt())
//                getIndicator()
                refresh()

            }

        })

    }


    fun setLiveData() {
        ViewModel.liveDataHome.observe(viewLifecycleOwner, Observer {



            if (it?.size != 0 ) {

                var listCategory= it[0].data?.category
                var listProdak=it[0].data?.productPromo
                listCategory?.forEachIndexed { index, category ->

                    tabCategory.newTab()?.setCustomView(R.layout.tab_category)
                    tabCategory.newTab()?.customView?.findViewById<TextView>(R.id.tv_title)?.text=category.name
//                    var imageView:ImageView= tabCategory.newTab()?.customView?.findViewById<ImageView>(R.id.img_category)!!
//                    Glide.with(requireActivity()).load(category.imageUrl).into(imageView)
                }
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
