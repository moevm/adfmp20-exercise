package com.example.myapplication.Coordinator

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R

interface BaseCoordinately {
    /**
     * Отобразить или скрыть фрагмент
     * @param fragment фрагмент для отображения/скрытия
     * @param currentView текущее вью на экране, которое будет отображать
     * @param isNeedToShow нужно ли отображать фрагмент
     */
    fun present(fragment: Fragment, currentView: Int, isNeedToShow: Boolean)
    /**
     * Перейти на новую Activity
     * @param activityClass Активити для отображения
     * @param currentActivity текущее активити, с которого будет произведен переход
     * @param activityName Имя отображаемого активити, по дефолту выбирается базовое имя
     */
    fun present(activityClass: Class<*>, currentActivity: Activity, activityName: String?)
}

enum class IntentExtraKeys(val toString: String) {
    ActivityName("activityName")
}

open class BaseCoordinator(private val transactionManager: FragmentManager):
    BaseCoordinately {

    /**
     * Отобразить или скрыть фрагмент
     * @param fragment фрагмент для отображения/скрытия
     * @param currentView текущее вью на экране, которое будет отображать
     * @param isNeedToShow нужно ли отображать фрагмент
     */
    override fun present(fragment: Fragment, currentView: Int, isNeedToShow: Boolean) {
        val manager = transactionManager
        val transaction = manager.beginTransaction()
        transaction.add(currentView, fragment)
        when (isNeedToShow) {
            true  -> {  transaction.show(fragment) }
            false -> {  transaction.hide(fragment) }
        }
        transaction.commit()
    }

    /** Перейти на новую Activity
    * @param activityClass Активити для отображения
    * @param currentActivity текущее активити, с которого будет произведен переход
    * @param activityName Имя отображаемого активити, по дефолту выбирается базовое имя
    */
    override fun present(activityClass: Class<*>, currentActivity: Activity, activityName: String?) {
        val intent = Intent(currentActivity, activityClass)
        intent.putExtra(IntentExtraKeys.ActivityName.toString,
                        if (activityName.isNullOrEmpty())
                            activityClass.name
                        else activityName)
        currentActivity.startActivity(intent)
    }
}