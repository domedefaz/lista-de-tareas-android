package com.metodologiaDefaz.listadetareas

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Locale

class RecordatorioTareasWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val db = AppDatabase.getInstance(applicationContext)
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fechaHoy = formato.format(java.util.Date())
        val tareasPendientesHoy = db.tareaDao().getTareasPorFecha(fechaHoy)

        if (tareasPendientesHoy.isNotEmpty()) {
            mostrarNotificacion(tareasPendientesHoy.size)
        }
        return Result.success()
    }

    private fun mostrarNotificacion(cantidad: Int) {
        val notificacion = NotificationCompat.Builder(applicationContext, "canal_tareas_pendientes")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Tareas pendientes para hoy")
            .setContentText("Tienes $cantidad tarea(s) que vencen hoy")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission(applicationContext,
                Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationManagerCompat.from(applicationContext).notify(1001, notificacion)
        }
    }
}