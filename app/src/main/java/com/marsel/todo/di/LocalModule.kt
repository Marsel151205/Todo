package com.marsel.todo.di

import android.content.Context
import com.marsel.data.local.preferences.PreferencesHelper
import com.marsel.data.local.preferences.userdata.UserDataPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun providePreferencesHelper(@ApplicationContext context: Context) =
        PreferencesHelper(context)

    @Singleton
    @Provides
    fun provideUserDataPreferences(preferencesHelper: PreferencesHelper) =
        UserDataPreferences(preferencesHelper)
}