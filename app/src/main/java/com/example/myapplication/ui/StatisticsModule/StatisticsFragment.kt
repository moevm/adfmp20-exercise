package com.example.myapplication.ui.StatisticsModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import android.R.attr.animation
import com.anychart.anychart.*
import com.anychart.anychart.AnyChart.*


class StatisticsFragment : Fragment() {

    private lateinit var statisticsViewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statisticsViewModel =
            ViewModelProviders.of(this).get(StatisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)

        val bar3d = AnyChart.column()

        bar3d.setAnimation(true)

        bar3d.column(statisticsViewModel.firstTrainData as List<ValueDataEntry>)
        bar3d.column(statisticsViewModel.secondTrainData as List<ValueDataEntry>)

        val anyChartView: AnyChartView = root.findViewById(R.id.chartView)
        anyChartView.setChart(bar3d)
        return root
    }
}