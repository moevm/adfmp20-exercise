package com.example.myapplication.ui.StatisticsModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.anychart.anychart.AnyChart
import com.anychart.anychart.AnyChartView
import com.anychart.anychart.ValueDataEntry
import com.example.myapplication.R

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

        /*val firstTrainData = mutableListOf<ValueDataEntry>()
        val secondTrainData = mutableListOf<ValueDataEntry>()
        firstTrainData.add(ValueDataEntry("Поднятие рук вверх и в стороны", 12000))
        secondTrainData.add(ValueDataEntry("Поднятие рук в стороны", 18000))*/


        bar3d.column(statisticsViewModel.firstTrainData as List<ValueDataEntry>)
        bar3d.column(statisticsViewModel.secondTrainData as List<ValueDataEntry>)

        val anyChartView: AnyChartView = root.findViewById(R.id.chartView)
        anyChartView.setChart(bar3d)
        return root
    }
}