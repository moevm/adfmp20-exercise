package com.example.myapplication.ui.StatisticsModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anychart.anychart.ValueDataEntry

class StatisticsViewModel : ViewModel() {

    val firstTrainData = mutableListOf<ValueDataEntry>()
    val secondTrainData = mutableListOf<ValueDataEntry>()

    init {
        // TODO: Its a mock
        firstTrainData.add(ValueDataEntry("Поднятие рук вверх и в стороны", 27.8))
        secondTrainData.add(ValueDataEntry("Поднятие рук в стороны", 43.8))
    }
}