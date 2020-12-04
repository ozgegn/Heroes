package com.ozgegn.heroes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroesDao {

    @Query("SELECT * FROM heroes WHERE page=:page")
    fun getHeroes(page: Int): List<HeroEntity>?

    @Query("SELECT * FROM heroes WHERE name LIKE '%' || :query || '%'")
    fun searchHeroes(query: String): List<HeroEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeroes(hero: HeroEntity)

}