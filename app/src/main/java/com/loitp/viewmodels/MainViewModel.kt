package com.loitp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.utilities.LStoreUtil
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    val listChapLiveData: MutableLiveData<List<String>> = MutableLiveData()

    fun loadListChap() {
        ioScope.launch {
            showLoading(true)

            val string = LStoreUtil.readTxtFromAsset(assetFile = "db.sqlite")
            val listChap = string.split("#")
            listChapLiveData.postValue(listChap)

            showLoading(false)
        }
    }
}
