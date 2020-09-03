package com.tes.tesshtq.adapter

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import com.bumptech.glide.Glide
import com.tes.tesshtq.R
import com.tes.tesshtq.activty.DetailProdukActivity
import com.tes.tesshtq.model.ProdakSearch
import com.tes.tesshtq.model.ResponseHome
import kotlinx.android.synthetic.main.item_produk.view.*


class ListProdukLocalAdapter(private var ctx: Context) :
    RecyclerView.Adapter<ListProdukLocalAdapter.ViewHolder>(){
    private val ITEM_VIEW_TYPE_CONTENT = 0
    private val ITEM_VIEW_TYPE_LOADING = 1


    private var listdata: ArrayList<ProdakSearch>? = null
    private var listOrderIdGroup: ArrayList<String>? = ArrayList<String>()
    private var isLoadingAdded = false



    init {
        this.listdata = java.util.ArrayList<ProdakSearch>()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val layoutInflater = LayoutInflater.from(parent.context)
        ctx = parent.context


        return when (viewType) {
            ITEM_VIEW_TYPE_CONTENT -> ViewHolderContent(
                layoutInflater.inflate(R.layout.item_produk, parent, false)
            )
            else -> ViewHolderLoading(
                layoutInflater.inflate(R.layout.item_data_loading, parent, false)
            )
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, i: Int) {

        var tv_name_produk = holder.itemView.tv_name_produk
        var tv_harga = holder.itemView.tv_harga
        var imgProduk = holder.itemView.imgProduk
        var lnrl_item_produk = holder.itemView.lnrl_item_produk


        when (getItemViewType(i)) {
            ITEM_VIEW_TYPE_CONTENT -> {
                tv_name_produk.text =listdata?.get(i)?.title
                Glide.with(ctx).load(listdata?.get(i)?.imageUrl).into(imgProduk)
                tv_harga.setText(listdata?.get(i)?.price!!)

                var produk=ResponseHome.Produk()
                produk.id=listdata?.get(i)?.id
                produk.title=listdata?.get(i)?.title
                produk.description=listdata?.get(i)?.description
                produk.loved=listdata?.get(i)?.loved
                produk.price=listdata?.get(i)?.price
                produk.imageUrl=listdata?.get(i)?.imageUrl

                lnrl_item_produk.setOnClickListener {
                    var inten= Intent(ctx,DetailProdukActivity::class.java)
                    inten.putExtra("produk", produk)
                    ctx.startActivity(inten)

                }


            }
            else -> {
                /** nothing to do in here */
            }
        }


    }








    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getListData(): ArrayList<ProdakSearch>? {
        return listdata
    }


    override fun getItemCount(): Int {
        return if (listdata == null) 0 else listdata!!.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == listdata?.size!! - 1 && isLoadingAdded) ITEM_VIEW_TYPE_LOADING else ITEM_VIEW_TYPE_CONTENT
    }

    fun add(r: ProdakSearch) {

        listdata?.add(r)
        notifyItemInserted(listdata?.size!! - 1)
    }

    fun addAll(dataList: ArrayList<ProdakSearch>) {

        for (result in dataList) {
            add(result)
        }
    }

    fun remove(r: ProdakSearch) {
        val position = listdata?.indexOf(r)
        if (position != null) {
            if (position > -1) {
                listdata?.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    fun clear() {
        listOrderIdGroup=ArrayList<String>()
//        (ctx as ListPesananActivity).convertArrayOrder(listOrderIdGroup!!)
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }


    fun addLoadingFooter() {
        isLoadingAdded = true
//        add(Pesanan)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = listdata?.size?.minus(1)
        val result = position?.let { getItem(it) }

        if (result != null) {
            position.let { listdata?.removeAt(it) }
            position.let { notifyItemRemoved(it) }
        }
    }

    fun getItem(position: Int): ProdakSearch {
        return listdata?.get(position)!!
    }

    fun hideLoader() {
        isLoadingAdded = false

    }


    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

    inner class ViewHolderContent(itemView: View) : ViewHolder(itemView)

    inner class ViewHolderLoading(itemView: View?) : ViewHolder(itemView)


}