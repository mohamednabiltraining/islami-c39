package com.route.islamic39.dataStore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val datastore = DataStoreManager(application)
    val getMode = datastore.getMode().asLiveData(Dispatchers.IO)
    fun saveMode(mode: Boolean) {
        viewModelScope.launch {
            datastore.saveMode(mode)
        }
    }
}
