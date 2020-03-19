package com.example.myapplication.ui.TrainModule.ChooseTrain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Вью модель для отображения тренировок на выбор
 * @param exersicesArray список доступных тренировок
 */
class ChooseTrainViewModel(exersicesArray: Array<String>) : ViewModel() {

    val exersices: Array<String>

    init {
        exersices = exersicesArray
    }
}