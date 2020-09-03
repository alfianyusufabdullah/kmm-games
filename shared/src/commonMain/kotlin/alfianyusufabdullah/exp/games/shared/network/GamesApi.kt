package alfianyusufabdullah.exp.games.shared.network

import alfianyusufabdullah.exp.games.shared.entity.GameResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json as SerializerJson

class GamesApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = SerializerJson { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json = json)
        }
    }

    suspend fun getAllGames(): GameResponse =
        httpClient.get(GAMES_ENDPOINT)

    companion object {
        private const val GAMES_ENDPOINT = "https://api.rawg.io/api/games"
    }
}