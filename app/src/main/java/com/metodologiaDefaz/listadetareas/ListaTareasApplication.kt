package com.metodologiaDefaz.listadetareas

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class ListaTareasApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        crearCanalNotificaciones()
    }

    private fun crearCanalNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                "canal_tareas_pendientes",
                "Recordatorios de tareas",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notificaciones sobre tareas pendientes para hoy"
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(canal)
        }
    }
}