package com.hanin_sakhri.dev_test.xtendtv_webview.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hanin_sakhri.dev_test.xtendtv_webview.model.XhrRequest

class XHRViewModel : ViewModel() {
    private val visitedXHRList : MutableList<XhrRequest> = mutableListOf()
    val publicList :List<XhrRequest> =visitedXHRList
    fun addXhr(xhr: XhrRequest) {
        Log.d("Newxhr",xhr.url)
        visitedXHRList.add(xhr)
    }

    fun getXhrList(): List<XhrRequest> {
        return visitedXHRList.toList()
    }

}
