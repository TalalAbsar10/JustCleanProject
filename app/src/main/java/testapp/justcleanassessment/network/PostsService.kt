package testapp.justcleanassessment.network

import retrofit2.http.GET
import testapp.justcleanassessment.network.model.NetworkPosts

interface PostsService {
    @GET("posts")
    suspend fun getPosts(): List<NetworkPosts>
}