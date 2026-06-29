package com.metodologiaDefaz.listadetareas

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class FormularioTareaActivity : AppCompatActivity() {

    private val tareaViewModel: TareaViewModel by viewModels()
    private lateinit var dao: TareaDao

    private lateinit var tvTituloPantalla: android.widget.TextView
    private lateinit var tilTitulo: TextInputLayout
    private lateinit var etTitulo: TextInputEditText
    private lateinit var etDescripcion: TextInputEditText
    private lateinit var etFechaLimite: TextInputEditText
    private lateinit var btnGuardar: MaterialButton

    private var tareaIdActual: Int = -1
    private var modoEdicion = false
    private var tareaExistente: Tarea? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_tarea)

        dao = AppDatabase.getInstance(application).tareaDao()

        tvTituloPantalla = findViewById(R.id.tvTituloPantalla)
        tilTitulo = findViewById(R.id.tilTitulo)
        etTitulo = findViewById(R.id.etTitulo)
        etDescripcion = findViewById(R.id.etDescripcion)
        etFechaLimite = findViewById(R.id.etFechaLimite)
        btnGuardar = findViewById(R.id.btnGuardar)

        tareaIdActual = intent.getIntExtra("TAREA_ID", -1)
        modoEdicion = tareaIdActual != -1

        if (modoEdicion) {
            tvTituloPantalla.text = "Editar tarea"
            btnGuardar.text = "Guardar cambios"
            cargarDatosExistentes(tareaIdActual)
        }

        btnGuardar.setOnClickListener {
            if (validarFormulario()) {
                if (modoEdicion) {
                    actualizarTarea()
                } else {
                    crearTarea()
                }
            }
        }
    }

    private fun cargarDatosExistentes(id: Int) {
        lifecycleScope.launch {
            val tarea = dao.getById(id)
            tarea?.let {
                tareaExistente = it
                etTitulo.setText(it.titulo)
                etDescripcion.setText(it.descripcion)
                etFechaLimite.setText(it.fechaLimite)
            }
        }
    }

    private fun validarFormulario(): Boolean {
        val titulo = etTitulo.text.toString().trim()
        if (titulo.isEmpty()) {
            tilTitulo.error = "El título es obligatorio"
            return false
        }
        tilTitulo.error = null
        return true
    }

    private fun crearTarea() {
        val nuevaTarea = Tarea(
            titulo = etTitulo.text.toString().trim(),
            descripcion = etDescripcion.text.toString().trim(),
            fechaLimite = etFechaLimite.text.toString().trim(),
            completada = false,
            fechaCreacion = obtenerFechaActual()
        )
        tareaViewModel.insert(nuevaTarea)
        finish()
    }

    private fun actualizarTarea() {
        tareaExistente?.let { original ->
            val tareaActualizada = original.copy(
                titulo = etTitulo.text.toString().trim(),
                descripcion = etDescripcion.text.toString().trim(),
                fechaLimite = etFechaLimite.text.toString().trim()
            )
            tareaViewModel.update(tareaActualizada)
        }
        finish()
    }

    private fun obtenerFechaActual(): String {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formato.format(java.util.Date())
    }
}