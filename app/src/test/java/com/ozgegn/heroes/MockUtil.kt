package com.ozgegn.heroes

import com.ozgegn.heroes.data.local.HeroEntity

object MockUtil {

    fun mockHero(): HeroEntity = HeroEntity(
        id = 1,
        name = "Shuri",
        imageUrl = "https://image.ibb.co/hFrbCp/shuri.jpg",
        page = 0
    )

    fun mockHeroList(): List<HeroEntity> = listOf(mockHero())

}