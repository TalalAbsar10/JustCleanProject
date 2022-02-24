package testapp.justcleanassessment.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import testapp.justcleanassessment.database.DatabaseFavoriteItems

@JsonClass(generateAdapter = true)
data class NetworkFavoriteItems(

    @Json(name = "email")
    val email: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "postId")
    val postId: Int,
    @Json(name = "body")
    val body: String? = null,
    @Json(name = "id")
    val id: Int,
)

fun List<NetworkFavoriteItems>.asDatabaseModel(): List<DatabaseFavoriteItems> {
    return map {
        DatabaseFavoriteItems(
        id = it.id,
        postId = it.postId,
        name = it.name ?: "",
        email = it.email ?: "",
        body = it.body ?: ""
        )
    }

}