package com.tes.tesshtq.utils

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.tes.tesshtq.R
import java.text.DecimalFormat

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}

fun showToast(context: Context?, message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


fun myPrint(message: String?) {
    println("xmen $message")
}

fun mySnackBar(view: View, str: String) {
    val mSnackBar = Snackbar.make(
        view, str, Snackbar.LENGTH_LONG
    ).setAction("Action", null)
    mSnackBar.setActionTextColor(Color.BLUE)
    val mSnackBarView = mSnackBar.view
    mSnackBarView.setBackgroundColor(Color.RED)
    val textView = mSnackBarView.findViewById(R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    textView.textSize = 15f
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        textView.textAlignment = (View.TEXT_ALIGNMENT_CENTER)
    }
    mSnackBar.show()
}

fun changeToRupiah(price: Int): String {
    val formatter = DecimalFormat("#,###")
    val convert = formatter.format(price)
    val filter = convert.replace(",", ".")
    return "Rp. $filter"
}

fun changeWithoutRupiah(price: Int): String {
    val formatter = DecimalFormat("#,###")
    val convert = formatter.format(price)
    return convert.replace(",", ".")
}

fun loading(ctx: Context) {
    val progressDialog = ProgressDialog(ctx)
    progressDialog.setCancelable(false)
    progressDialog.setMessage("Silahkan Tunggu..")
}

fun alertDialogBiasa(context: Context?, message: String?) {
    val success = AlertDialog.Builder(context!!)
    success.setMessage(message)
    success.setPositiveButton("Ok") { _, _ ->

    }
    success.setCancelable(false)
    success.create().show()
}

fun myInflate(parent: ViewGroup, resource: Int) =
    LayoutInflater.from(parent.context).inflate(resource, parent, false) as View

