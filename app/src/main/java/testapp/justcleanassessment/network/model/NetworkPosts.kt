package testapp.justcleanassessment.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import testapp.justcleanassessment.database.DatabasePosts

@JsonClass(generateAdapter = true)
data class NetworkPosts(

    @Json(name = "userId")
    val userId: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "body")
    val body: String,
    @Json(name = "id")
    val id: Int,
)

fun List<NetworkPosts>.asDatabaseModel(): List<DatabasePosts> {
    return map {
        DatabasePosts(
            id = it.id,
            body = it.body,
            title = it.title,
            userId = it.userId
        )
    }
}