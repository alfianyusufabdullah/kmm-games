package alfianyusufabdullah.exp.games.gamesAndroid

import alfianyusufabdullah.exp.games.shared.GamesSDK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alfianyusufabdullah.exp.games.shared.Greeting
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val gamesSDK = GamesSDK()
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        mainScope.launch {
            kotlin.runCatching {
                gamesSDK.getAllGames()
            }.onSuccess {
                tv.text = it.games.size.toString()
            }.onFailure {
                Log.d("ERRRRROR" ,it.message)
                Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}
