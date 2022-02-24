package testapp.justcleanassessment.network

import testapp.justcleanassessment.domain.CommentsItems
import testapp.justcleanassessment.network.model.NetworkFavoriteItems

interface FavoriteItemsService {
    suspend fun getFavoriteItems(data:CommentsItems): List<NetworkFavoriteItems>
}