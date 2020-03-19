package com.example.myapplication

import android.os.Bundle
import com.example.myapplication.Coordinator.BaseCoordinatelyActivity
import com.example.myapplication.Coordinator.IntentExtraKeys
import com.example.myapplication.ui.TrainModule.Train.TrainFragment


class TrainActivity: BaseCoordinatelyActivity() {

    private val trainFragment = TrainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_train)

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
