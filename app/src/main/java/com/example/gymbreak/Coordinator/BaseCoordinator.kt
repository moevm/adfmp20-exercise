package com.example.gymbreak.Coordinator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gymbreak.R

interface BaseCoordinately {
    fun present(fragment: Fragment)
}

open class BaseCoordinator(private val transactionManager: FragmentManager):
    BaseCoordinately {

    private val rootView = R.id.nav_host_fragment

    override fun present(fragment: Fragment) {
        val manager = transactionManager
        val transaction = manager.beginTransaction()
        transaction.replace(rootView, fragment)
        transaction.commit()
    }
}