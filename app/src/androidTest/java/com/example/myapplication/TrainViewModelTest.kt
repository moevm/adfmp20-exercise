package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anychart.anychart.ValueDataEntry
import com.example.myapplication.ui.StatisticsModule.StatisticsViewModel
import com.example.myapplication.ui.TrainModule.Train.CacheManager
import com.example.myapplication.ui.TrainModule.Train.RepeatModel
import com.example.myapplication.ui.TrainModule.Train.TrainViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import kotlin.math.absoluteValue

@RunWith(AndroidJUnit4::class)
class TrainViewModelTest {

    private val trainViewModel = TrainViewModel()
    @Test
    fun checkThatResultIsCorrectWithCorrectData() {
        trainViewModel.setTrainData(RepeatModel(10.0, 10.0, 0.0))
        assertEquals(100.0, trainViewModel.getResult())
    }

    @Test
    fun checkThatResultIsCorrectWithIncorrectData() {
        trainViewModel.setTrainData(RepeatModel(10.0, 10.0, 10.0))
        assertEquals(100.0, trainViewModel.getResult())
    }

    @Test
    fun checkThatResultIsCorrectWithNoiseData() {
        trainViewModel.setTrainData(RepeatModel(0.0, 0.0, 0.0))
        assertEquals(null, trainViewModel.getResult())
    }
}