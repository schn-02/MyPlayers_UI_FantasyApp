package com.example.myplayer.UpperNavigationView.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplayer.Model.SavedTeamListResponse
import com.example.myplayer.Retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyTeamViewModel : ViewModel() {

    private val _myTeams = MutableStateFlow<List<SavedTeamListResponse>>(emptyList())
    val myTeams: StateFlow<List<SavedTeamListResponse>> = _myTeams

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getMySavedTeams() {
        viewModelScope.launch {

            _isLoading.value = true

            try {

                val response = RetrofitClient.AuthData.getMySavedTeams()

                if (response.isSuccessful && response.body() != null) {

                    _myTeams.value = response.body()!!

                    Log.d("MY_TEAMS", "Teams: ${response.body()}")

                } else {

                    Log.e("MY_TEAMS", "Code: ${response.code()}")
                    Log.e("MY_TEAMS", "Error: ${response.errorBody()?.string()}")
                }

            } catch (e: Exception) {

                Log.e("MY_TEAMS", "Exception: ${e.message}", e)

            } finally {

                _isLoading.value = false
            }
        }
    }
}