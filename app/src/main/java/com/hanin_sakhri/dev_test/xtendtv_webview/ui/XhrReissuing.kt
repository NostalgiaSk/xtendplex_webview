package com.hanin_sakhri.dev_test.xtendtv_webview.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import com.hanin_sakhri.dev_test.xtendtv_webview.R



class XhrReissuing : AppCompatActivity() {

    private lateinit var responseWebView :WebView


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xhr_reissuing)

        responseWebView = findViewById(R.id.webview_response)

        val responseURL = intent.getStringExtra("responseURL")
        Log.d("responseBody", responseURL.toString())

        val webSettings: WebSettings = responseWebView.settings
        webSettings.javaScriptEnabled = true

        if (responseURL != null) {
            responseWebView.loadUrl(responseURL.toString())
        }

        WebView.setWebContentsDebuggingEnabled(true)



    }
}