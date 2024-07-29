package com.marsel.todo.di

import com.marsel.data.repositories.UserDataRepositoryImpl
import com.marsel.domain.repositories.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindUserDataRepository(userDataRepositoryImpl: UserDataRepositoryImpl): UserDataRepository
}