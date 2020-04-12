package com.example.myapplication.ui.StatisticsModule

import android.content.Context
import androidx.lifecycle.ViewModel
import com.anychart.anychart.ValueDataEntry
import com.example.myapplication.ui.TrainModule.Train.APP_PREFERENCES_TRAIN_RESULT_KEY
import com.example.myapplication.ui.TrainModule.Train.CacheManager

class StatisticsViewModel(context: Context) : ViewModel() {

    private val cacheManager: CacheManager = CacheManager(context, APP_PREFERENCES_TRAIN_RESULT_KEY)

    fun getDayViewModel(): MutableList<MutableList<ValueDataEntry>> {
        val trainResult: MutableList<Double> = cacheManager.getTrainsResultByCurrentDay()
        val viewModel: MutableList<MutableList<ValueDataEntry>> = mutableListOf()
        for (training in trainResult) {
            val columnViewModel = mutableListOf<ValueDataEntry>()
            columnViewModel.add(ValueDataEntry("\"Прошедшая тренировка. Качество: ${training.toString().take(4)} %", training))
            viewModel.add(columnViewModel)
        }
        return viewModel
    }

    val dayTrainingsCount: Int
    get() = cacheManager.getTrainsResultByCurrentDay().count()

    val weekTrainingsCount: Int
    get() {
        var count: Int = 0
        for (day in 1..8) {
            count += cacheManager.getTrainsResultByDay(day).count()
        }
        return count
    }

    fun getWeekViewModel(): MutableList<MutableList<ValueDataEntry>> {
        val viewModel: MutableList<MutableList<ValueDataEntry>> = mutableListOf()

        for (day in 1..8) {
            val trainingsList = cacheManager.getTrainsResultByDay(day)
            val dayQuality = trainingsList.sum() / trainingsList.count()
            val columnViewModel = mutableListOf<ValueDataEntry>()
            columnViewModel.add(ValueDataEntry("\"День ${getDayOfWeek(day)}. Качество: ${dayQuality.toString().take(4)} %", dayQuality))
            viewModel.add(columnViewModel)
        }
        return viewModel
    }

    private fun getDayOfWeek(number: Int): String {
        when (number) {
            1 -> return "Воскресенье"
            2 -> return "Понедельник"
            3 -> return "Вторник"
            4 -> return "Среда"
            5 -> return "Четверг"
            6 -> return "Пятница"
            7 -> return "Суббота"
        }
        return ""
    }
}