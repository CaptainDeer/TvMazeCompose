package com.example.tvmazecompose.core.di

import android.content.Context
import androidx.room.Room
import com.example.tvmazecompose.BuildConfig
import com.example.tvmazecompose.data.api.MovieAPI
import com.example.tvmazecompose.domain.MovieDao
import com.example.tvmazecompose.domain.MovieDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }.build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        baseUrl(BuildConfig.API_BASE_URL)
        client(okHttpClient)
        addConverterFactory(MoshiConverterFactory.create())
    }.build()

    @Provides
    @Singleton
    fun providesMovieAPI(retrofit: Retrofit): MovieAPI =
        retrofit.create(MovieAPI::class.java)

    @Provides
    @Singleton
    fun movieDataSource(@ApplicationContext context: Context): MovieDataSource {
        return Room.databaseBuilder(context, MovieDataSource::class.java, "TVMaze_Database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun movieDao(db: MovieDataSource): MovieDao = db.movieDao()

}