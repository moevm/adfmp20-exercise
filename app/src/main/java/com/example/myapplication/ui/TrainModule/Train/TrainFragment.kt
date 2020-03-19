package com.example.myapplication.ui.TrainModule.Train

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R

class TrainFragment : Fragment() {

    private lateinit var trainViewModel: TrainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trainViewModel =
            ViewModelProviders.of(this).get(TrainViewModel::class.java)
        val rootView = inflater.inflate(R.layout.fragment_train, container, false)
        return rootView
    }
}