package com.example.myapplication


import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.ui.TrainModule.Train.TrainResultModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class TrainResultModelTest {

    private val trainResultViewModel = TrainResultModel()
    @Test
    fun checkThatResultIsLow() {
        assertEquals(TrainResultModel.TrainResult.Low, trainResultViewModel.getTrainResult(23.4))
    }

    @Test
    fun checkThatResultIsMiddle() {
        assertEquals(TrainResultModel.TrainResult.Middle, trainResultViewModel.getTrainResult(60.0))
    }

    @Test
    fun checkThatResultIsHigh() {
        assertEquals(TrainResultModel.TrainResult.High, trainResultViewModel.getTrainResult(75.0))
    }
}