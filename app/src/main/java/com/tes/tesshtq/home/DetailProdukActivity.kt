package com.tes.tesshtq.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tes.tesshtq.R
import com.tes.tesshtq.utils.setSystemBarColor
import kotlinx.android.synthetic.main.activity_detail_produk.*

class DetailProdukActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var  produk: ResponseHome.Produk

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)
        viewModel = HomeViewModel(application)

        setUi()
    }



    fun setUi() {

        setSystemBarColor(this, R.color.white)
        img_back.setOnClickListener {
            finish()
        }


       produk=  intent.getParcelableExtra<ResponseHome.Produk>("produk")as ResponseHome.Produk

        tv_name_produk.text=produk.title
        tv_deskrip_produk.text=produk.description
        tv_harga.text=produk.price
        Glide.with(this).load(produk.imageUrl).into(imgProduk)
        var isFavorite = produk.loved


        if (isFavorite==1){
            btnIsFavorite.setImageResource(R.drawable.ic_baseline_favorite_24_true)
        }
        else{
            btnIsFavorite.setImageResource(
                R.drawable.ic_baseline_favorite_border_24_false
            )

        }

        btnIsFavorite.setOnClickListener {
            if (isFavorite==1) {
                btnIsFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24_false)
                isFavorite=0

            } else {
                btnIsFavorite.setImageResource(R.drawable.ic_baseline_favorite_24_true)
                isFavorite=1

            }

        }

        img_shere.setOnClickListener {
            shere(intent.getStringExtra("nameProduk").toString())
        }

        bt_buy.setOnClickListener {
            viewModel.insertProduk(produk)

        }




    }




    fun shere(name:String){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "https://produktHiss3l-0hDgo/view?usp=$name"
        )
        startActivity(Intent.createChooser(intent, "Bagikan Produk Ini"))
    }






















}
