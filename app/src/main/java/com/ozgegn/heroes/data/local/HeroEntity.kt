package com.ozgegn.heroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")
data class HeroEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val page: Int = 0
)