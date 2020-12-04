package com.ozgegn.heroes.repository

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.ozgegn.heroes.base.BaseRepository
import com.ozgegn.heroes.data.local.HeroEntity
import com.ozgegn.heroes.data.local.HeroesDao
import com.ozgegn.heroes.data.remote.HeroesSingleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val heroesDao: HeroesDao,
    private val heroesSingleton: HeroesSingleton
) : BaseRepository {

    override var isLoading: ObservableBoolean = ObservableBoolean(false)
    private val URL =
        "https://gist.githubusercontent.com/AniketSK/d4d9e03d5d2fdfb83199dbb2605e8cf6/raw/49983e4225cf2f53ab9d29e3a3b6ed35805518c8/SampleResponse.json"

    suspend fun fetchHeroes(page: Int, onError: (String?) -> Unit) = withContext(Dispatchers.IO) {
        val liveDataHeroList = MutableLiveData<List<HeroEntity>>()
        var heroData = heroesDao.getHeroes(page)
        if (heroData.isNullOrEmpty()) {
            isLoading.set(true)
            val heroesList = mutableListOf<HeroEntity>()
            val heroesObjRequest =
                JsonObjectRequest(Request.Method.GET, URL, null, Response.Listener { response ->
                    val heroes = response.getJSONArray("characters")
                    for (i in 0 until heroes.length()) {
                        val jsonObj = heroes.getJSONObject(i)
                        val hero = HeroEntity(
                            id = jsonObj.getInt("id"),
                            name = jsonObj.getString("name"),
                            imageUrl = jsonObj.getJSONObject("image").getString("url"),
                            page = i % 10
                        )
                        heroesDao.insertHeroes(hero)
                        heroesList.add(hero)
                    }
                    liveDataHeroList.postValue(heroesList)
                },
                    Response.ErrorListener { error -> onError(error?.message) }

                )
            heroesSingleton.addToRequestQueue(heroesObjRequest)
        }
        liveDataHeroList.apply { postValue(heroData) }
    }

    suspend fun filterHeroes(query: String, onError: (String?) -> Unit) = withContext(Dispatchers.IO) {
        val liveDataHeroList = MutableLiveData<List<HeroEntity>>()
        var heroData = heroesDao.searchHeroes(query)
        liveDataHeroList.apply { postValue(heroData) }
    }


}