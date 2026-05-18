package com.example.myplayer.UpperNavigationView.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayer.Model.SavedTeamDetailResponse
import com.example.myplayer.Retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SavedTeamPreviewViewModel : ViewModel() {

    private val _teamDetails = MutableStateFlow<SavedTeamDetailResponse?>(null)
    val teamDetails: StateFlow<SavedTeamDetailResponse?> = _teamDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getSavedTeamDetails(teamId: Long) {

        viewModelScope.launch {

            _isLoading.value = true

            try {

                val response = RetrofitClient.AuthData.getSavedTeamDetails(teamId)

                if (response.isSuccessful && response.body() != null) {

                    _teamDetails.value = response.body()

                    Log.d("SAVED_PREVIEW", "Team: ${response.body()}")

                } else {

                    Log.e("SAVED_PREVIEW", "Code: ${response.code()}")
                    Log.e("SAVED_PREVIEW", "Error: ${response.errorBody()?.string()}")
                }

            } catch (e: Exception) {

                Log.e("SAVED_PREVIEW", "Exception: ${e.message}", e)

            } finally {

                _isLoading.value = false
            }
        }
    }
}