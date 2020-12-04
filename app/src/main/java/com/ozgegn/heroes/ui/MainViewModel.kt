package com.ozgegn.heroes.ui

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.ozgegn.heroes.base.LiveCoroutinesViewModel
import com.ozgegn.heroes.data.local.HeroEntity
import com.ozgegn.heroes.repository.HeroesRepository

class MainViewModel @ViewModelInject constructor(
    private val heroesRepository: HeroesRepository
) : LiveCoroutinesViewModel() {

    fun isLoading(): ObservableBoolean = heroesRepository.isLoading

    private var fetchHeroesLiveData: MutableLiveData<Int> = MutableLiveData()
    private var filterHeroesLiveData: MutableLiveData<String> = MutableLiveData()

    val errorMessage: MutableLiveData<String?> = MutableLiveData()
    val heroes: LiveData<List<HeroEntity>>
    val filteredHeroes: LiveData<List<HeroEntity>>

    init {
        heroes = fetchHeroesLiveData.switchMap { page ->
            launchOnViewModelScope {
                heroesRepository.fetchHeroes(page) {
                    errorMessage.postValue(it)
                }
            }
        }

        filteredHeroes = filterHeroesLiveData.switchMap { query ->
            launchOnViewModelScope {
                heroesRepository.filterHeroes(query) {
                    errorMessage.postValue(it)
                }
            }
        }
    }

    fun fetchHeroes(page: Int) {
        fetchHeroesLiveData.postValue(page)
    }

    fun searchHeroes(query: String) {
        filterHeroesLiveData.postValue(query)
    }

}