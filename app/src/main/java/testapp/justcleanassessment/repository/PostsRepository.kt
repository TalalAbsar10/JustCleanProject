package testapp.justcleanassessment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import testapp.justcleanassessment.database.UsersDatabase
import testapp.justcleanassessment.database.asDomainModel
import testapp.justcleanassessment.domain.PostsItems
import testapp.justcleanassessment.network.PostsService
import testapp.justcleanassessment.network.model.asDatabaseModel
import timber.log.Timber
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postsService:PostsService,
    private val database: UsersDatabase
) {

    val users: LiveData<List<PostsItems>> =
        Transformations.map(database.usersDao.getDatabasePosts()) {
            it.asDomainModel()
        }

    suspend fun refreshPosts() {
        try {
            val users = postsService.getPosts()
            database.usersDao.insertPosts(users.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}