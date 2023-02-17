package com.richmondprojects.rickandmorty.di

import com.richmondprojects.rickandmorty.data.repository.RepositoryImpl
import com.richmondprojects.rickandmorty.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}