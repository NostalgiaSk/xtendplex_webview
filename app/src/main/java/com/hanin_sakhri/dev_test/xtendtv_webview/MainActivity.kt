package com.hanin_sakhri.dev_test.xtendtv_webview

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hanin_sakhri.dev_test.xtendtv_webview.utils.XtendtvWebViewClient

class MainActivity : AppCompatActivity() {

    private lateinit var xtendtvWebView :WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         xtendtvWebView = findViewById(R.id.xtendtv)

        if (!isNetworkAvailable()) {
            showErrorDialog("Error", "No internet connection. Please check your connection.",
                this@MainActivity)
        }
        else{
            //Run the webView
            xtendtvWebView.webViewClient = XtendtvWebViewClient()
            xtendtvWebView.apply {
                loadUrl("https://google.com")
                settings.javaScriptEnabled = true
            }
        }


        // Enable WebView debugging
        WebView.setWebContentsDebuggingEnabled(true)

        xtendtvWebView.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                // Extract and process console messages here
                val message = consoleMessage?.message() ?: ""
                // Handle XHR messages and display them
                if (message.contains("XMLHttpRequest")) {
                    Log.d("Xhr","$message")
                }
                return true
            }
        }



    }









    //manage backPress
    override fun onBackPressed() {
        if (xtendtvWebView.canGoBack())
        {
            xtendtvWebView.goBack()
        }
        else
            super.onBackPressed()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectionManager =
            this@MainActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    private fun showErrorDialog(title: String, message: String, context: Context) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setNegativeButton("Cancel") { _, _ ->
            this@MainActivity.finish()
        }
        dialog.setNeutralButton("Settings") { _, _ ->
            startActivity(Intent(Settings.ACTION_SETTINGS))
        }
        dialog.setPositiveButton("Retry") { _, _ ->
            this@MainActivity.recreate()
        }
        dialog.create().show()
    }


}