package com.example.myapplication.ui.TrainModule.Train

import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.math.absoluteValue
import android.content.SharedPreferences




class TrainResultModel {

    enum class TrainResult(val border: Double) { Low(0.0), Middle(58.5), High(70.0) }

    fun getTrainResult(sum: Double): TrainResult {
        return getResultedQuality(sum)
    }

    /// Оцениваем результат
    private fun getResultedQuality(averageSum: Double): TrainResult {
        when (averageSum) {
            in TrainResult.Low.border..TrainResult.Middle.border -> return TrainResult.Low
            in TrainResult.Middle.border..TrainResult.High.border -> return TrainResult.Middle
            else -> return TrainResult.High
        }
    }

}

class TrainViewModel : ViewModel() {

    private var repeats: MutableList<RepeatModel> = mutableListOf()
    private var inProgressRepeatsCount: Int = 0
    private var minimumRepeatResult: Double = 5.0

    fun setTrainData(repeatModel: RepeatModel): Int {
        repeats.add(filterNoiseData(repeatModel))
        if (isResultSatisfactioned()) { inProgressRepeatsCount += 1 }
        return inProgressRepeatsCount
    }

    fun getResult(): Double? {
        var result = getResultedSums() * 100
        inProgressRepeatsCount = 0
        if (result > 0.0) {
            return if (result > 100.0) 100.0 else result
        }
        return null
    }

    // MARK: - Private methods

    /// трекаем, является ли выполненный повтор достаточным для засчитывания
    private fun isResultSatisfactioned(): Boolean {
        val lastRepeat: RepeatModel = repeats.last()
        var beforeLastRepeat: RepeatModel = RepeatModel(0.0, 0.0, 0.0)
        if (repeats.count() <= 1) {
            beforeLastRepeat = repeats[repeats.lastIndex]
        }
        val beforeLastSum = (beforeLastRepeat.x + beforeLastRepeat.y) / 2
        var resultSum: Double = (lastRepeat.x + lastRepeat.y) / 2
        return resultSum >= minimumRepeatResult && resultSum > beforeLastSum + 5.9
    }

    /// Суммируем полученные от аксилерометра значения
    private fun getResultedSums(): Double {
        val xFilteredResult = repeats.filter { repeatModel -> repeatModel.x != 0.0 }
        val xResultSum: Double = xFilteredResult.map { repeatModel -> repeatModel.x }.sum() / xFilteredResult.count()

        val yFilteredResult = repeats.filter { repeatModel -> repeatModel.y != 0.0 }
        val yResultSum: Double = xFilteredResult.map { repeatModel -> repeatModel.y }.sum() / yFilteredResult.count()

        val averageSum: Double = (xResultSum + yResultSum) / 2
        return averageSum
    }


    /// Отфильтровать данные с шумом
    private fun filterNoiseData(repeatModel: RepeatModel): RepeatModel {
        if (repeatModel.x.absoluteValue < 1) { repeatModel.x = 0.0 }
        if (repeatModel.y.absoluteValue < 1) { repeatModel.y = 0.0 }
        if (repeatModel.z.absoluteValue < 1) { repeatModel.z = 0.0 }
        return repeatModel
    }
}
class RepeatModel(var x: Double, var y: Double, var z: Double)