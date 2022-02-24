package testapp.justcleanassessment.di

import testapp.justcleanassessment.BuildConfig
import testapp.justcleanassessment.network.CommentsService
import testapp.justcleanassessment.network.PostsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import testapp.justcleanassessment.network.FavoriteItemsService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun postsService(retrofit: Retrofit): PostsService =
        retrofit.create(PostsService::class.java)

    @Provides
    @Singleton
    fun commentsService(retrofit: Retrofit): CommentsService =
        retrofit.create(CommentsService::class.java)

    @Provides
    @Singleton
    fun provideFavoriteItemsService(retrofit: Retrofit): FavoriteItemsService =
        retrofit.create(FavoriteItemsService::class.java)

}
