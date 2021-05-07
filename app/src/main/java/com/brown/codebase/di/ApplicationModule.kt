package com.brown.codebase.di

import com.brown.codebase.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Provides
    fun provideApp() = App.instance

}
