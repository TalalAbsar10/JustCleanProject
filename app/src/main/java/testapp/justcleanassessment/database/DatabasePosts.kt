package testapp.justcleanassessment.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import testapp.justcleanassessment.domain.PostsItems

@Entity
data class DatabasePosts constructor(

    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)

fun List<DatabasePosts>.asDomainModel(): List<PostsItems> {

    return map {
        PostsItems(
            id = it.id,
            title = it.title,
            body = it.body,
            userId=it.userId
        )
    }
}