package com.example.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.WeatherRepository
import com.example.data.model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.data.view.Result

//private val Result.Companion.Loading: Result<WeatherResponse>

class WeatherViewModel(
    private val repository: WeatherRepository = WeatherRepository()
) : ViewModel() {

    // UI:n tila: Loading, Success tai Error
    // MutableStateFlow = muutettava versio (vain ViewModelin sisällä)
    private val _weatherState = MutableStateFlow<Result<WeatherResponse>>(Result.Loading)
    // StateFlow = vain luettava versio (UI kuuntelee tätä)
    val weatherState: StateFlow<Result<WeatherResponse>> = _weatherState.asStateFlow()

    // Hakukenttä tila
    private val _searchQuery = MutableStateFlow("Helsinki")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun searchWeather() {
        val city = _searchQuery.value
        if (city.isBlank()) return

        // viewModelScope.launch käynnistää coroutinen taustasäikeessä
        viewModelScope.launch {
            _weatherState.value = Result.Loading         // Näytä latausindikaattori
            _weatherState.value = repository.getWeather(city)  // Hae data
        }
    }
}