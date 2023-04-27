package com.example.tvmazecompose.core.di

import com.example.tvmazecompose.data.MovieRepositoryImpl
import com.example.tvmazecompose.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Erik Hernandez on 25/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun movieRepository(repo: MovieRepositoryImpl): MovieRepository
}