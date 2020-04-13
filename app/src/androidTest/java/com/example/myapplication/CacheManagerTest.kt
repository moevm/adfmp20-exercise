package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.ui.StatisticsModule.StatisticsViewModel
import com.example.myapplication.ui.TrainModule.Train.APP_PREFERENCES_TRAIN_RESULT_KEY
import com.example.myapplication.ui.TrainModule.Train.CacheManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.*

@RunWith(AndroidJUnit4::class)
class CacheManagerTest {

    private val testKey = "CacheManagerTestKey"
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val cacheManager = CacheManager(appContext, testKey)

    @Test
    fun checkThatDataIsSettingCorrectlyForTheDay() {
        beforeEach()
        val expectedResult = 71.0
        cacheManager.setTrainingResult(expectedResult)
        assertEquals(cacheManager.getTrainsResultByDay(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)), mutableListOf(expectedResult))
    }

    private fun beforeEach() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val cache: SharedPreferences = appContext.getSharedPreferences(
            testKey, AppCompatActivity.MODE_PRIVATE)
        cache.edit().clear()
    }
}
