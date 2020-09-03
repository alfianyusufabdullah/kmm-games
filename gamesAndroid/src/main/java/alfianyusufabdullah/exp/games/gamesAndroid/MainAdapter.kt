package alfianyusufabdullah.exp.games.gamesAndroid

import alfianyusufabdullah.exp.games.shared.entity.Game
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import java.text.DecimalFormat

class MainAdapter(
    private val games: MutableList<Game>
) :
    RecyclerView.Adapter<MainAdapter.GameHolder>() {

    fun submitList(gamesNew: List<Game>) {
        this.games.clear()
        this.games.addAll(gamesNew)

        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: GameHolder) {
        super.onViewRecycled(holder)

        Glide.with(holder.itemView).clear(holder.released)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameHolder(view)
    }

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.bind(games[position])
    }

    class GameHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.itemName)
        private val rating: TextView = view.findViewById(R.id.itemRating)
        val released: TextView = view.findViewById(R.id.itemReleased)

        private val background: ImageView = view.findViewById(R.id.itemBackground)

        fun bind(game: Game) {
            rating.text = DecimalFormat("#.0").format(game.rating)
            name.text = game.name
            released.text = game.released

            Glide.with(itemView).load(game.background).apply {
                apply(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
                skipMemoryCache(true)
                override(100)
                into(background)
            }
        }
    }
}