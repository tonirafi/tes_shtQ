package com.tes.tesshtq.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import com.tes.tesshtq.R


class ListProdukAdapter(private var ctx: Context) :
    RecyclerView.Adapter<ListProdukAdapter.ViewHolder>(){
    private val ITEM_VIEW_TYPE_CONTENT = 0
    private val ITEM_VIEW_TYPE_LOADING = 1


    private var listdata: ArrayList<ResponseHome.Produk>? = null
    private var listOrderIdGroup: ArrayList<String>? = ArrayList<String>()
    private var isLoadingAdded = false



    init {
        this.listdata = java.util.ArrayList<ResponseHome.Produk>()

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

//        var tv_name_prodak = holder.itemView.tv_name_prodak
//        var tv_qty = holder.itemView.tv_qty
//        var tv_harga_satuan = holder.itemView.tv_harga_satuan
//        var tv_total_harga = holder.itemView.tv_total_harga
//        var tv_note = holder.itemView.tv_note
//        var imgProduk = holder.itemView.imgProduk


        when (getItemViewType(i)) {
            ITEM_VIEW_TYPE_CONTENT -> {
//                tv_name_prodak.text =listdata?.get(i)?.productsName
//                tv_qty.text="Jumlah : "+listdata?.get(i)?.qtyOrders!!
//                tv_note.text=listdata?.get(i)?.noteItem!!
//                Glide.with(ctx).load(listdata?.get(i)?.productsImages).placeholder(R.drawable.loading_animation)
//                    .into(imgProduk)
//                tv_harga_satuan.setText("Harga : Rp "+ Tools.getDecimal(listdata?.get(i)?.price!!))
//
//                var hargaSatuan=listdata?.get(i)?.price
//                var qty=listdata?.get(i)?.qtyOrders
//                var total= hargaSatuan!! * qty!!
//                tv_total_harga.text = "Total Harga : Rp "+Tools.getDecimal(total)


            }
            else -> {
                /** nothing to do in here */
            }
        }


    }








    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getListData(): ArrayList<ResponseHome.Produk>? {
        return listdata
    }


    override fun getItemCount(): Int {
        return if (listdata == null) 0 else listdata!!.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == listdata?.size!! - 1 && isLoadingAdded) ITEM_VIEW_TYPE_LOADING else ITEM_VIEW_TYPE_CONTENT
    }

    fun add(r: ResponseHome.Produk) {

        listdata?.add(r)
        notifyItemInserted(listdata?.size!! - 1)
    }

    fun addAll(dataList: ArrayList<ResponseHome.Produk>) {

        for (result in dataList) {
            add(result)
        }
    }

    fun remove(r: ResponseHome.Produk) {
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

    fun getItem(position: Int): ResponseHome.Produk {
        return listdata?.get(position)!!
    }

    fun hideLoader() {
        isLoadingAdded = false

    }


    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

    inner class ViewHolderContent(itemView: View) : ViewHolder(itemView)

    inner class ViewHolderLoading(itemView: View?) : ViewHolder(itemView)


}