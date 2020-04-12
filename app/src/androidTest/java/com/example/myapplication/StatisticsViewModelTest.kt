package com.example.myapplication


import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anychart.anychart.ValueDataEntry
import com.example.myapplication.ui.StatisticsModule.StatisticsViewModel
import com.example.myapplication.ui.TrainModule.Train.APP_PREFERENCES_TRAIN_RESULT_KEY
import com.example.myapplication.ui.TrainModule.Train.CacheManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class StatisticsViewModelTest {

    private val testKey = APP_PREFERENCES_TRAIN_RESULT_KEY
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val cacheManager = CacheManager(appContext, testKey)
    private val statisticsViewModel = StatisticsViewModel(appContext)

    @Test
    fun checkDayTrainingsCount() {
        val expectedCount = cacheManager.getTrainsResultByCurrentDay().count()
        assertEquals(statisticsViewModel.dayTrainingsCount, expectedCount)
    }

    @Test
    fun checkWeekTrainingsCount() {
        var expectedCount = 0
        for (day in 1..8) {
            expectedCount += cacheManager.getTrainsResultByDay(day).count()
        }
        assertEquals(statisticsViewModel.weekTrainingsCount, expectedCount)
    }

    @Test
    fun checkDayViewModel() {
        val trainResult: MutableList<Double> = cacheManager.getTrainsResultByCurrentDay()
        val viewModel: MutableList<MutableList<ValueDataEntry>> = mutableListOf()
        for (training in trainResult) {
            val columnViewModel = mutableListOf<ValueDataEntry>()
            columnViewModel.add(ValueDataEntry("\"Прошедшая тренировка. Качество: ${training.toString().take(4)} %", training))
            viewModel.add(columnViewModel)
        }
        assertEquals(statisticsViewModel.getDayViewModel(), viewModel)
    }
}
