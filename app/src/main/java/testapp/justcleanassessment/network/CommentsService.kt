package testapp.justcleanassessment.network

import testapp.justcleanassessment.network.model.NetworkComments
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsService {

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int): List<NetworkComments>

}