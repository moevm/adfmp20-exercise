package com.example.myapplication

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.ui.TrainModule.ChooseTrain.ChooseTrainViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class ChooseTrainViewModelTest {
    @Test
    fun checkTrainingVariants() {
        val nameArray: Array<String> = arrayOf("first training", "second training")
        val trainingsViewModel = ChooseTrainViewModel(nameArray)
        assertEquals(trainingsViewModel.exersices, nameArray)
    }
}
