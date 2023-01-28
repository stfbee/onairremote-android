package ru.ovm.onair.display

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _data: MutableLiveData<State> = MutableLiveData()
    val data: LiveData<State> = _data

    private var pollerJob: Job? = null

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    init {
        pollerJob = viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                fetchData()
                delay(1000)
            }
        }
    }

    private suspend fun fetchData() {
        try {
            val state: State = client.get(endpoint).body()
            _data.postValue(state)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCleared() {
        super.onCleared()
        client.close()
        pollerJob?.cancel()
        pollerJob = null
    }

    companion object {
        const val endpoint = "http://192.168.100.9:4200/"
    }
}