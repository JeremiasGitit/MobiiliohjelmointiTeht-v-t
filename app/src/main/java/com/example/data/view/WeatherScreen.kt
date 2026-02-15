package com.example.data.view

/*import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.AsyncImage*/

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

import com.example.data.viewModel.WeatherViewModel
import com.example.data.model.WeatherResponse

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    // collectAsState() muuntaa StateFlown Compose-tilaksi
    val searchQuery by viewModel.searchQuery.collectAsState()
    val weatherState by viewModel.weatherState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Hakukenttä
        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.onSearchQueryChange(it) },
            onSearch = { viewModel.searchWeather() }
        )

        // Säätiedot - when käsittelee kaikki Result-tilat
        when (val state = weatherState) {
            is Result.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is Result.Success -> {
                WeatherContent(weather = state.data)
            }
            is Result.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = state.exception.message ?: "Virhe tapahtui",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.searchWeather() }) {
                        Text("Yritä uudelleen")
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherContent(weather: WeatherResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())  // Scrollattava sisältö
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Kaupunki ja maa
        Text(
            text = "${weather.name}, ${weather.sys.country}",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sääkuvake (ladataan verkosta Coil-kirjastolla)
        val iconUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}@4x.png"
        AsyncImage(
            model = iconUrl,
            contentDescription = "Sääkuvake",
            modifier = Modifier.size(120.dp)
        )

        // Lämpötila
        Text(
            text = "${weather.main.temp.toInt()}°C",
            style = MaterialTheme.typography.displayLarge
        )

        // Kuvaus (esim. "pilvistä" → "Pilvistä")
        Text(
            text = weather.weather[0].description.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Lisätiedot kortissa
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                WeatherDetailRow("Tuntuu kuin", "${weather.main.feels_like.toInt()}°C")
                WeatherDetailRow("Min / Max", "${weather.main.tempMin.toInt()}°C / ${weather.main.tempMax.toInt()}°C")
                WeatherDetailRow("Kosteus", "${weather.main.humidity}%")
                WeatherDetailRow("Tuuli", "${weather.wind.speed} m/s")
                WeatherDetailRow("Paine", "${weather.main.pressure} hPa")
            }
        }
    }
}

// Yksittäinen tietorivi: "Kosteus    85%"
@Composable
fun WeatherDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}