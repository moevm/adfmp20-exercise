package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.Coordinator.BaseCoordinatelyActivity
import com.example.myapplication.Coordinator.IntentExtraKeys
import com.example.myapplication.ui.TrainModule.Train.TrainFragment
import com.example.myapplication.ui.TrainModule.Train.TrainViewModel


class TrainActivity: BaseCoordinatelyActivity(), SensorEventListener {

    private val trainFragment = TrainFragment()

    private lateinit var mSensorManager: SensorManager
    private lateinit var trainViewModel: TrainViewModel
    private var mSensors: Sensor? = null
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
//        Sensor change value
        val millibarsOfPressure = p0!!.values[0]
        Log.d("warning here types", p0.sensor.type.toString())
        if (p0.sensor.type == Sensor.TYPE_ACCELEROMETER && p0.values[0] != 0.toFloat() && p0.values[1] != 0.toFloat() && p0.values[2] != 0.toFloat() ){
            Log.d("warning here changes", "x = ${p0.values[0].toString()}\n\n y = ${p0.values[1].toString()} \n\n z = ${p0.values[2].toString()}")
        }
    }

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
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        Define the sensor
        Log.d("warning here", "000")
        mSensors = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        coordinator.present(trainFragment, currentView = R.id.activity_train, isNeedToShow = true)
        setActionBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // MARK: - Private methods

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
