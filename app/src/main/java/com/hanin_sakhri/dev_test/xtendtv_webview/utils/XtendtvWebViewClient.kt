package com.hanin_sakhri.dev_test.xtendtv_webview.utils

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.hanin_sakhri.dev_test.xtendtv_webview.model.XhrRequest

class XtendtvWebViewClient : WebViewClient() {

    private val xhrRequests = mutableListOf<XhrRequest>()

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
        Toast.makeText(view.context, "URL : $url", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)

    }


    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        Log.d("interceptXHR", "${request?.method} ${request?.url}")
        return super.shouldInterceptRequest(view, request)
    }


}