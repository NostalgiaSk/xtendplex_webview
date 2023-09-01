package com.hanin_sakhri.dev_test.xtendtv_webview.ui

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
import android.widget.Button
import com.hanin_sakhri.dev_test.xtendtv_webview.R
import com.hanin_sakhri.dev_test.xtendtv_webview.utils.XtendtvWebViewClient
import com.hanin_sakhri.dev_test.xtendtv_webview.viewModel.XHRViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var xtendtvWebView :WebView
    private val viewModel = XHRViewModel()


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

            xtendtvWebView.webViewClient = XtendtvWebViewClient(viewModel)
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
                    Log.d("Xhr", message)
                }
                return true
            }
        }

        val trackButton = findViewById<Button>(R.id.track_urls)
        trackButton.setOnClickListener{

            try {
                val customDialog = CustomDialog(this, viewModel.getXhrList())
                customDialog.show()
            }catch (e:Exception){Log.d("dialogError",e.toString())}
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

    //check network availability
    private fun isNetworkAvailable(): Boolean {
        val connectionManager =
            this@MainActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    //Error dialog in case no network available
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




