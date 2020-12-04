package com.ozgegn.heroes.di

import android.app.Application
import androidx.room.Room
import com.ozgegn.heroes.data.local.AppDatabase
import com.ozgegn.heroes.data.local.HeroesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "heroes.db")
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideHeroesDao(appDatabase: AppDatabase): HeroesDao = appDatabase.getHeroesDao()

}