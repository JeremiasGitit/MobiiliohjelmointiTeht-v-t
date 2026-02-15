package com.example.data.view

// Compose perusjutut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

// Tekstikenttä + näppäimistö
import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardOptions
//import androidx.compose.ui.text.input.KeyboardActions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

// Iconit
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

@Composable
fun SearchBar(
    query: String,                    // Nykyinen hakuteksti
    onQueryChange: (String) -> Unit,  // Kutsutaan kun teksti muuttuu
    onSearch: () -> Unit              // Kutsutaan kun käyttäjä painaa "Hae"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Kaupunki") },
            modifier = Modifier.weight(1f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search    // Näppäimistön "Hae"-painike
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }       // Enter = hae
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onSearch) {
            Icon(Icons.Default.Search, "Hae")
        }
    }
}