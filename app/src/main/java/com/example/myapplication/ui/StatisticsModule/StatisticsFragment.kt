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

        val data1 = mutableListOf<ValueDataEntry>()
        val data2 = mutableListOf<ValueDataEntry>()
        val data3 = mutableListOf<ValueDataEntry>()
        data1.add(ValueDataEntry("Поднятие рук вверх", 10000))
        data2.add(ValueDataEntry("Поднятие рук вверх и в стороны", 12000))
        data3.add(ValueDataEntry("Поднятие рук в стороны", 18000))


        val line1 = bar3d.column(data1 as List<ValueDataEntry>)

        bar3d.column(data2 as List<ValueDataEntry>)//.setColor("#673AB7")
        bar3d.column(data3 as List<ValueDataEntry>)


        /*line1.setHeight(20.0)
        line1.setColor("#00574B")
        line1.setWidth(20.0)*/

        val anyChartView: AnyChartView = root.findViewById(R.id.chartView)
        anyChartView.setChart(bar3d)
        return root
    }
}