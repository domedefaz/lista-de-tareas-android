package com.metodologiaDefaz.listadetareas

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TareaDao {

    @Insert
    suspend fun insert(tarea: Tarea): Long

    @Query("SELECT * FROM tareas ORDER BY fechaLimite ASC")
    fun getAll(): LiveData<List<Tarea>>

    @Update
    suspend fun update(tarea: Tarea)

    @Delete
    suspend fun delete(tarea: Tarea)

    @Query("SELECT * FROM tareas WHERE tareaId = :id")
    suspend fun getById(id: Int): Tarea?

    @Query("SELECT * FROM tareas WHERE fechaLimite = :fecha AND completada = 0")
    suspend fun getTareasPorFecha(fecha: String): List<Tarea>
}