package com.ozgegn.heroes

import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.ozgegn.heroes.data.local.AppDatabase
import com.ozgegn.heroes.data.local.HeroesDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DaoTest {

    private lateinit var dao: HeroesDao
    private lateinit var db: AppDatabase

    @Before
    fun init(){
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase::class.java)
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
        dao = db.getHeroesDao()
    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun testInsertAndGetDataFromDb() = runBlocking {
        val mockHeroTest = MockUtil.mockHero()

        dao.insertHeroes(mockHeroTest)
        val loadFromDb = dao.getHeroes(0) ?: listOf()

        Assert.assertThat(loadFromDb[0].toString(), `is`(mockHeroTest.toString()))

    }

}