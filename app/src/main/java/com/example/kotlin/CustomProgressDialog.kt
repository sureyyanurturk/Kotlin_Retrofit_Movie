package com.example.kotlin

import android.app.Activity
import android.app.Dialog
import android.content.Context
import kotlinx.android.synthetic.main.progress_dialog_view.view.*
import java.util.zip.Inflater

class CustomProgressDialog {
    lateinit var dialog: Dialog

    fun show(context: Context, title:CharSequence?) {
        val inflater = (context as Activity).layoutInflater
        val view=inflater.inflate(R.layout.progress_dialog_view,null)
        if (title != null) {
            view.progressTitle.text = title
        }
        dialog= Dialog(context)
        dialog.setContentView(view)
        dialog.show()

    }
}