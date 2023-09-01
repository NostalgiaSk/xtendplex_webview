package com.hanin_sakhri.dev_test.xtendtv_webview.utils

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hanin_sakhri.dev_test.xtendtv_webview.model.XhrRequest
import com.hanin_sakhri.dev_test.xtendtv_webview.viewModel.XHRViewModel

class XtendtvWebViewClient(private val xhrViewModel: XHRViewModel) : WebViewClient() {

    private var xhrRequests = mutableListOf<XhrRequest>()

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
        Toast.makeText(view.context, "URL : $url", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        xhrViewModel.addXhr(XhrRequest(url,""))
//        xhrRequests = xhrViewModel.getXhrList() as MutableList<XhrRequest>
//        Log.d("List" ,"$xhrRequests")

    }


    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        Log.d("interceptXHR", "${request?.method} ${request?.url}")
        return super.shouldInterceptRequest(view, request)
    }


}