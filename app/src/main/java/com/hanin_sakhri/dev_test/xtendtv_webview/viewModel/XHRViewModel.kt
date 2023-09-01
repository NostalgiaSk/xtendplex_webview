package com.hanin_sakhri.dev_test.xtendtv_webview.viewModel

import androidx.lifecycle.ViewModel
import com.hanin_sakhri.dev_test.xtendtv_webview.model.XhrRequest

class XHRViewModel : ViewModel() {
    private val visitedXHRList : MutableList<XhrRequest> = mutableListOf()

    fun addXhr(xhr: XhrRequest) {
        visitedXHRList.add(xhr)
    }

    fun getXhrList(): List<XhrRequest> {
        return visitedXHRList.toList()
    }

}