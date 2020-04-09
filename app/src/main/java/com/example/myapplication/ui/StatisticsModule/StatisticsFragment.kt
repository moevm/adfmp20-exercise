package com.example.myapplication.ui.StatisticsModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import android.R.attr.animation
import android.util.Log
import android.widget.TextView
import androidx.core.view.isVisible
import com.anychart.anychart.*
import com.anychart.anychart.AnyChart.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_statistics.view.*


class StatisticsFragment : Fragment() {

    private lateinit var statisticsViewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        statisticsViewModel = StatisticsViewModel(context!!)
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)
        configureChart(root, false)
        configureChart(root, true)
        setTabListener(root)
        return root
    }

    private fun setTabListener(root: View) {
        val tabView: TabLayout = root.findViewById(R.id.tabLayout)
        tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val dayChartView: AnyChartView = root.findViewById(R.id.dayChartView)
                val weekChartView: AnyChartView = root.findViewById(R.id.weekChartView)

                val dayHeader: TextView = root.findViewById(R.id.dayCountRepeatHeader)
                val weekHeader: TextView = root.findViewById(R.id.weeklyCountRepeatHeader)
                when (tab.position) {
                    0 -> { dayChartView.isVisible = true; weekChartView.isVisible = false; dayHeader.isVisible = true; weekHeader.isVisible = false }
                    1 -> { dayChartView.isVisible = false; weekChartView.isVisible = true; dayHeader.isVisible = false; weekHeader.isVisible = true }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun configureChart(root: View = view!!.rootView, isDaily: Boolean = true) {
        val bar3d = column()

        bar3d.setAnimation(true)

        val viewModel = if (isDaily) statisticsViewModel.getDayViewModel() else statisticsViewModel.getWeekViewModel()
        for (columnViewModel in viewModel) {
            bar3d.column(columnViewModel as List<ValueDataEntry>)
        }

        val header: TextView = if (isDaily) root.findViewById(R.id.dayCountRepeatHeader) else root.findViewById(R.id.weeklyCountRepeatHeader)
        header.text = "Количество тренировок: ${if (isDaily) statisticsViewModel.dayTrainingsCount else statisticsViewModel.weekTrainingsCount }"

        val anyChartView: AnyChartView = if (isDaily) root.findViewById(R.id.dayChartView) else root.findViewById(R.id.weekChartView)
        anyChartView.setChart(bar3d)
    }
}