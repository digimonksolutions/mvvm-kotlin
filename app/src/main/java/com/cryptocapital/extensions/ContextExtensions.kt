package com.cryptocapital.extensions

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast

fun Context.showAlertDialog(
    msg: String,
    positiveClickListener: DialogInterface.OnClickListener? = null,
    positive: String? = null,
    negativeClickListener: DialogInterface.OnClickListener? = null,
    negative: String? = null,
    cancelable: Boolean = true
): AlertDialog {
    val alertDialog = AlertDialog.Builder(this).setMessage(msg)
    alertDialog.setCancelable(cancelable)
    if (positiveClickListener != null && !positive.isNullOrEmpty()) {
        alertDialog.setPositiveButton(
            positive,
            positiveClickListener
        )
    }
    if (negativeClickListener != null && !negative.isNullOrEmpty()) {
        alertDialog.setNegativeButton(
            negative,
            negativeClickListener
        )
    }
    return alertDialog.show()
}

fun Context.toastMessage(message:String?){
    Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
}



