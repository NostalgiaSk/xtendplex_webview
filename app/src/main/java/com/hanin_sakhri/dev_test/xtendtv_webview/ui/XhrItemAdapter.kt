package com.hanin_sakhri.dev_test.xtendtv_webview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanin_sakhri.dev_test.xtendtv_webview.R
import com.hanin_sakhri.dev_test.xtendtv_webview.model.XhrRequest

class XhrItemAdapter(private val xhrRequests: List<XhrRequest>) : RecyclerView.Adapter<XhrItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {
        val xhrMethod: TextView = itemView.findViewById(R.id.xhr_method)
        val xhrBody: TextView = itemView.findViewById(R.id.xhr_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.xhr_item_layout,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return xhrRequests.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val xhr = xhrRequests[position]
        holder.xhrMethod.text = xhr.method
        holder.xhrBody.text = xhr.url
    }
}