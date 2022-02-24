package testapp.justcleanassessment.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsersDao {

    @Query("select * from DatabasePosts")
    fun getDatabasePosts(): LiveData<List<DatabasePosts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(users: List<DatabasePosts>)

    @Query("select * from DatabaseComments WHERE postId LIKE :userId")
    fun getDatabaseComments(userId: Int): LiveData<List<DatabaseComments>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(databaseUserDetails: List<DatabaseComments>)

    @Query("select * from DatabaseFavoriteItems")
    fun getFavoriteItems(): LiveData<List<DatabaseFavoriteItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteItems(databasefavoriteitems: DatabaseFavoriteItems)

}

@Database(entities = [DatabasePosts::class, DatabaseComments::class,DatabaseFavoriteItems::class], version = 9)
abstract class UsersDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}
