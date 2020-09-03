package alfianyusufabdullah.exp.games.gamesAndroid

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val rvGames: RecyclerView by lazy { findViewById(R.id.rvGames) }
    private val rvGamesAdapter: MainAdapter by lazy { MainAdapter(mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGames.hasFixedSize()
        rvGames.layoutManager = LinearLayoutManager(this)
        rvGames.adapter = rvGamesAdapter
        rvGames.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                with(outRect) {
                    if (parent.getChildAdapterPosition(view) == 0) {
                        top = 15
                    }
                    bottom = 15
                }
            }
        })

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.games.observe(this) {
            if (it.isEmpty()) {
                Toast.makeText(this@MainActivity, "Something happen!", Toast.LENGTH_SHORT).show()
            } else {
                rvGamesAdapter.submitList(it)
            }
        }
    }
}
