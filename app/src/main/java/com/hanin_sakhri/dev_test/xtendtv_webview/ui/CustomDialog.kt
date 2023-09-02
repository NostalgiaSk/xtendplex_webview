package com.hanin_sakhri.dev_test.xtendtv_webview.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanin_sakhri.dev_test.xtendtv_webview.R
import com.hanin_sakhri.dev_test.xtendtv_webview.model.XhrRequest
import okhttp3.*
import java.io.IOException

class CustomDialog(
    private val activity: Activity,
    private val xhrItems : List<XhrRequest>) : Dialog(activity) {

    private lateinit var recyclerView: RecyclerView
    private val client = OkHttpClient()
    private val activeRequests = mutableListOf<Request>()
    private var responseBody: String? = null
    private var responseURL: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.costum_dialog_layout)
        recyclerView = findViewById(R.id.xhr_recyclerView)
        val adapter = XhrItemAdapter(xhrItems)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter


        adapter.onItemClick ={ xhrRequest ->
            val request = Request.Builder()
                .url(xhrRequest.url)
                .method(xhrRequest.method, null)
                .build()
            if (isSimilarRequestActive(request)) {
                // Handle the case where a similar request is already active
            } else {
                activeRequests.add(request)

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        // Handle the failure here
                        activeRequests.remove(request)
                    }

                    override fun onResponse(call: Call, response: Response) {
                         responseBody = response.body?.string().toString()
                        responseURL =response.request.url.toString()
                        Log.d("response",responseBody.toString())
                        Log.d("urlBase",response.request.url.toString())

                        activity.runOnUiThread {
                            activeRequests.remove(request)
                        }
                    }
                })
            }


            val intent = Intent(context,XhrReissuing::class.java)
            intent.putExtra("responseBody", responseBody)
            intent.putExtra("responseURL", responseURL)
            activity.startActivity(intent)

        }
    }


    private fun isSimilarRequestActive(newRequest: Request): Boolean {
        return activeRequests.any { it.url == newRequest.url && it.method == newRequest.method }
    }
}