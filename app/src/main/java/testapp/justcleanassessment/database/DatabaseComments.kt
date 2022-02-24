package testapp.justcleanassessment.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import testapp.justcleanassessment.domain.CommentsItems

@Entity
data class DatabaseComments constructor(
    @PrimaryKey
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)

fun List<DatabaseComments>.asDomainModel(): List<CommentsItems> {
    return map {
        CommentsItems(
            body = it.body,
            email = it.email,
            id = it.id,
            name = it.name,
            postId = it.postId
        )
    }
}