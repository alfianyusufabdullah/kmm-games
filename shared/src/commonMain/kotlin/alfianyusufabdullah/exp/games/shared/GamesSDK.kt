package alfianyusufabdullah.exp.games.shared

import alfianyusufabdullah.exp.games.shared.entity.GameResponse
import alfianyusufabdullah.exp.games.shared.network.GamesApi

class GamesSDK {
    private val api = GamesApi()

    @Throws(Exception::class)
    suspend fun getAllGames(): GameResponse = api.getAllGames()
}