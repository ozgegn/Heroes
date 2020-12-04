package com.ozgegn.heroes.di

import android.app.Application
import com.ozgegn.heroes.data.remote.HeroesSingleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHeroesSingleton(application: Application) = HeroesSingleton.getInstance(application)

}