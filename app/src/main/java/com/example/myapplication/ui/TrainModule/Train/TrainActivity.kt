package com.example.myapplication.ui.TrainModule.Train

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.Coordinator.BaseCoordinatelyActivity
import com.example.myapplication.Coordinator.IntentExtraKeys
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activty_train.*
import org.w3c.dom.Text
import java.util.*


val APP_PREFERENCES_TRAIN_RESULT_KEY = "trainResultKey"


class TrainActivity: BaseCoordinatelyActivity(), SensorEventListener {

    private enum class TrainActivityState { NotStarted, InProgress, Finished }

    private val trainFragment = TrainFragment()
    private lateinit var trainViewModel: TrainViewModel
    private var state: TrainActivityState = TrainActivityState.NotStarted

    // MARK: - SensorEventListener conforming

    private lateinit var mSensorManager: SensorManager
    private var mSensors: Sensor? = null

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
//        Sensor change value
        if (p0?.sensor?.type == Sensor.TYPE_ACCELEROMETER && state == TrainActivityState.InProgress) {
            vibratePhone()
            trainViewModel.setTrainData(RepeatModel(p0.values[0].toDouble(), p0.values[1].toDouble(), p0.values[2].toDouble()))
        }
    }

    // MARK: - Activity lifecycle

    override fun onResume() {
        super.onResume()
//        Register the sensor on resume of the activity
        mSensorManager.registerListener(this, mSensors, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
//        unregister the sensor onPause else it will be active even if the activity is closed
        mSensorManager.unregisterListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        trainViewModel =
            ViewModelProviders.of(this).get(TrainViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_train)
        configureSensors()
        addButtonClickHandling()
        setActionBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        val result = trainViewModel.getResult()
        if (result != null) {
            saveResultInCache(result)
        }
    }

    // MARK: - Navigation bar configuration

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // MARK: - Private methods

    // MARK: Vibration handling

    private fun vibratePhone() {
        val vibrator: Vibrator = applicationContext?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }

    private fun addButtonClickHandling() {
        val startButton: Button = findViewById(R.id.startTrainButton)
        val header: TextView = findViewById(R.id.trainResult)
        val percentResult: TextView = findViewById(R.id.percentValue)
        startButton.setOnClickListener {
            when (state) {
                TrainActivityState.NotStarted, TrainActivityState.Finished -> {
                    startButton.text = "Завершить"
                    header.text = "Рассчитываем результат..."
                    percentValue.isVisible = false
                    state = TrainActivityState.InProgress
                }
                TrainActivityState.InProgress -> {
                    startButton.text = "Повторить"
                    percentValue.isVisible = true
                    header.text = "Результат тренировки"
                    state = TrainActivityState.Finished

                    val summaryResult: Double = trainViewModel.getResult() ?: 0.0
                    val trainResult = TrainResultModel().getTrainResult(summaryResult)
                    setResultedColor(trainResult)
                    percentResult.text = "${summaryResult.toString().take(4)} %"

                }
            }
        }
    }

    private fun setResultedColor(result: TrainResultModel.TrainResult) {
        val percentResult: TextView = findViewById(R.id.percentValue)
        when(result) {
            TrainResultModel.TrainResult.High -> percentResult.setTextColor(Color.GREEN)
            TrainResultModel.TrainResult.Middle -> percentResult.setTextColor(Color.YELLOW)
            TrainResultModel.TrainResult.Low -> percentResult.setTextColor(Color.RED)
        }
    }

    private fun saveResultInCache(result: Double) {
        val cacheManager = CacheManager(this.applicationContext, APP_PREFERENCES_TRAIN_RESULT_KEY)
        cacheManager.setTrainingResult(result)
    }

    private fun configureSensors() {
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        Define the sensor
        mSensors = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    /**
     * Задаем имя на навбаре:
     * имя активити = имени выбранной тренировки
     */
    private fun setActionBar() {
        supportActionBar?.title = intent.extras?.getString(IntentExtraKeys.ActivityName.toString)
        //set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
