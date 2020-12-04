package com.ozgegn.heroes.di

import com.ozgegn.heroes.data.local.HeroesDao
import com.ozgegn.heroes.data.remote.HeroesSingleton
import com.ozgegn.heroes.repository.HeroesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideHeroesRepository(
        heroesDao: HeroesDao,
        heroesSingleton: HeroesSingleton
    ): HeroesRepository = HeroesRepository(heroesDao, heroesSingleton)


}