package com.metodologiaDefaz.listadetareas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TareaViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).tareaDao()

    val tareas: LiveData<List<Tarea>> = dao.getAll()

    fun insert(tarea: Tarea) {
        viewModelScope.launch {
            dao.insert(tarea)
        }
    }

    fun update(tarea: Tarea) {
        viewModelScope.launch {
            dao.update(tarea)
        }
    }

    fun delete(tarea: Tarea) {
        viewModelScope.launch {
            dao.delete(tarea)
        }
    }
}