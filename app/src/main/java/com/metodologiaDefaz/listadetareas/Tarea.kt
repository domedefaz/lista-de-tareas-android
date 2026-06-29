package com.metodologiaDefaz.listadetareas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val tareaId: Int = 0,
    val titulo: String,
    val descripcion: String = "",
    val completada: Boolean = false,
    val fechaCreacion: String,
    val fechaLimite: String = "",
    val fechaCompletado: String? = null
)