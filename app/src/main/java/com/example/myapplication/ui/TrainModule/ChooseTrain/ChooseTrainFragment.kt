package com.example.myapplication.ui.TrainModule.ChooseTrain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.BaseSpinner.BaseSpinner
import com.example.myapplication.Coordinator.BaseCoordinatelyActivity
import com.example.myapplication.R
import com.example.myapplication.ui.TrainModule.Train.TrainActivity

class ChooseTrainFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var chooseTrainViewModel: ChooseTrainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Init ViewModel part
        val exsersicesResources: Array<String> = resources.getStringArray(R.array.trainTypes)
        chooseTrainViewModel = ChooseTrainViewModel(exsersicesResources)

        val rootView = inflater.inflate(R.layout.fragment_choose_train, container, false)
        setSpinnerListener(rootView)
        return rootView
    }

    /**
     * Флаг, что выбор тренировки уже сделан
     * нужен, чтобы в неактивном состоянии
     * не была выбрана первая тренировка в списке
     */
    private var isAlreadySelected: Boolean = false

    /**
     * Конфигурация спиннера для активити
     * @param rootView активити, содержащее спинер
     */
    private fun setSpinnerListener(rootView: View) {
        val spinner: BaseSpinner = rootView.findViewById(R.id.trainVariants)

        ArrayAdapter.createFromResource(
            rootView.context,
            R.array.trainTypes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this
    }


    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        view.visibility = View.INVISIBLE
        if (isAlreadySelected) {
            (activity as BaseCoordinatelyActivity)
                .coordinator
                .present(
                    TrainActivity::class.java,
                         currentActivity = (activity as AppCompatActivity),
                         activityName = chooseTrainViewModel.exersices.get(pos))
        }
        isAlreadySelected = true
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}
}