package testapp.justcleanassessment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import testapp.justcleanassessment.database.DatabaseFavoriteItems
import testapp.justcleanassessment.database.UsersDatabase
import testapp.justcleanassessment.database.asDomainModel
import testapp.justcleanassessment.domain.CommentsItems
import testapp.justcleanassessment.network.CommentsService
import testapp.justcleanassessment.network.model.asDatabaseModel
import timber.log.Timber
import javax.inject.Inject

class CommentsRepository @Inject constructor(
    private val commentsService: CommentsService,
    private val database: UsersDatabase,
) {

    fun getUserDetails(id: Int): LiveData<List<CommentsItems>>{
        return Transformations.map(database.usersDao.getDatabaseComments(id)) {
            it?.asDomainModel()
        }
    }

    suspend fun refreshComments(id: Int) {
        try {
            val userDetails = commentsService.getComments(id)
            database.usersDao.insertComments(userDetails.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

    fun getFavoritesItems(): LiveData<List<DatabaseFavoriteItems>>{
        return database.usersDao.getFavoriteItems()
    }

    fun refreshFavoritesItems(data:CommentsItems) {
        try {
            val favoriteItems = DatabaseFavoriteItems(data.body,
                data.email,
                data.id,
                data.name,
                data.postId)
            database.usersDao.insertFavoriteItems(favoriteItems)
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

}