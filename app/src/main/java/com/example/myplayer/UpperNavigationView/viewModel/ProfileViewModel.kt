package com.example.myplayer.UpperNavigationView.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayer.Model.UserDetialsModel
import com.example.myplayer.Retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _userDetails = MutableStateFlow(UserDetialsModel())
    val userDetails: StateFlow<UserDetialsModel> = _userDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun getUserDetails() {

        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = ""

            try {

                val response = RetrofitClient.AuthData.getDetails()

                if (response.isSuccessful && response.body() != null) {

                    _userDetails.value = response.body()!!

                    Log.d("PROFILE_DATA", "User: ${response.body()}")

                } else {

                    val error = response.errorBody()?.string() ?: "Unable to fetch profile"

                    _errorMessage.value = error

                    Log.e("PROFILE_DATA", "Code: ${response.code()}")
                    Log.e("PROFILE_DATA", "Error: $error")
                }

            } catch (e: Exception) {

                _errorMessage.value = e.message ?: "Something went wrong"

                Log.e("PROFILE_DATA", "Exception: ${e.message}", e)

            } finally {

                _isLoading.value = false
            }
        }
    }
}