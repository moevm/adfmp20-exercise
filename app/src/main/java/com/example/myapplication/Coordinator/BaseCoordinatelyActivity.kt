package com.example.myapplication.Coordinator

import androidx.appcompat.app.AppCompatActivity

/**
 * Интерфейс Activity,
 * Вся координация в приложении через его кастинг
 */
interface ActivityCoordinately {
    val coordinator: BaseCoordinator
}

/**
 * Базовый класс Activity приложения
 * содержит координатор, отвечающий за презентацию новых активити
 * и фрагментов
 */
open class BaseCoordinatelyActivity: AppCompatActivity(), ActivityCoordinately {

    override val coordinator = BaseCoordinator(supportFragmentManager)


}