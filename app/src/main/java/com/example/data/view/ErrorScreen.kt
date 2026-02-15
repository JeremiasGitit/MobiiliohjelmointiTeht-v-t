//package com.example.data.view
/*
// Compose
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error

// ViewModel & StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Retrofit / IO / HTTP Exceptions
import java.io.IOException
import retrofit2.HttpException

// Data models & repository (oma paketti)
import com.example.data.model.User
import com.example.data.model.RetrofitClient

// Result sealed class
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

// Repository-kerros
class UserRepository {
    suspend fun getUsers(): Result<List<User>> {
        return try {
            val users = RetrofitClient.apiService.getUsers()
            Result.Success(users)
        } catch (e: IOException) {
            Result.Error(Exception("Verkkovirhe: ${e.message}"))
        } catch (e: HttpException) {
            Result.Error(Exception("Palvelinvirhe: ${e.code()}"))
        } catch (e: Exception) {
            Result.Error(Exception("Tuntematon virhe: ${e.message}"))
        }
    }
}

// ViewModel
class UserViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {
    private val _userState = MutableStateFlow<Result<List<User>>>(Result.Loading)
    val userState: StateFlow<Result<List<User>>> = _userState.asStateFlow()

    fun loadUsers() {
        viewModelScope.launch {
            _userState.value = Result.Loading
            _userState.value = repository.getUsers()
        }
    }
}

// UI
@Composable
fun UserListScreen(viewModel: UserViewModel = viewModel()) {
    val state by viewModel.userState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    when (val result = state) {
        is Result.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is Result.Success -> {
            LazyColumn {
                items(result.data) { user ->
                    UserItem(user)
                }
            }
        }
        is Result.Error -> {
            ErrorScreen(
                message = result.exception.message ?: "Tuntematon virhe",
                onRetry = { viewModel.loadUsers() }
            )
        }
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.Error,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Yritä uudelleen")
        }
    }
}*/