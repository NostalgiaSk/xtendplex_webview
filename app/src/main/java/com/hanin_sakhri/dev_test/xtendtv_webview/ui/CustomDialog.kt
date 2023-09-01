package com.hanin_sakhri.dev_test.xtendtv_webview.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanin_sakhri.dev_test.xtendtv_webview.R
import com.hanin_sakhri.dev_test.xtendtv_webview.model.XhrRequest

class CustomDialog(
    context: Context,
    private val xhrItems : List<XhrRequest>) : Dialog(context) {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.costum_dialog_layout)
        recyclerView = findViewById(R.id.xhr_recyclerView)
        val adapter = XhrItemAdapter(xhrItems)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}