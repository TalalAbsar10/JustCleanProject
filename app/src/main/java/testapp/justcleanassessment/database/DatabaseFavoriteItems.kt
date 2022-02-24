package testapp.justcleanassessment.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import testapp.justcleanassessment.domain.FavoriteItems

@Entity
data class DatabaseFavoriteItems constructor(
    @PrimaryKey
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)

fun List<DatabaseFavoriteItems>.asDomainModel(): List<FavoriteItems> {
    return map {
        FavoriteItems(
            body = it.body,
            email = it.email,
            id = it.id,
            name = it.name,
            postId = it.postId
        )
    }
}