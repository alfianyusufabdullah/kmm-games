package alfianyusufabdullah.exp.games.gamesAndroid

import alfianyusufabdullah.exp.games.shared.GamesSDK
import alfianyusufabdullah.exp.games.shared.entity.Game
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val gamesSDK = GamesSDK()

    private val _games = MutableLiveData<List<Game>>()
    val games = _games

    init {
        getAllGames()
    }

    private fun getAllGames() {
        viewModelScope.launch {
            kotlin.runCatching {
                gamesSDK.getAllGames()
            }.onSuccess {
                _games.postValue(it.games)
            }.onFailure {
                Log.d("ERROR", it.message ?: "error")
                _games.postValue(listOf())
            }
        }
    }
}