package com.example.gymbreak.ui.TrainModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.gymbreak.ActivityCoordinately
import com.example.gymbreak.R

class ChooseTrain(): Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var chooseTrainViewModel: ChooseTrainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chooseTrainViewModel =
            ViewModelProviders.of(this).get(ChooseTrainViewModel::class.java)
        val rootView = inflater.inflate(R.layout.fragment_choose_train, container, false)
        setSpinnerListener(rootView)
        return rootView
    }

    private fun setSpinnerListener(rootView: View) {
        val spinner: Spinner = rootView.findViewById(R.id.trainVariants)

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

    //MARK: - AdapterView.OnItemSelectedListener

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        view.visibility = View.INVISIBLE
        (activity as ActivityCoordinately).coordinator.present(TrainFragment())
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

}