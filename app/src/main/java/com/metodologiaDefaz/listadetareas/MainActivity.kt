package com.metodologiaDefaz.listadetareas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val tareaViewModel: TareaViewModel by viewModels()
    private lateinit var adapter: TareaAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notificaciones activadas", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No podrás recibir recordatorios de tareas", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTareas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = TareaAdapter(
            tareas = emptyList(),
            onItemClick = { tarea ->
                val intent = Intent(this, FormularioTareaActivity::class.java)
                intent.putExtra("TAREA_ID", tarea.tareaId)
                startActivity(intent)
            },
            onCheckboxClick = { tarea ->
                val tareaActualizada = tarea.copy(completada = !tarea.completada)
                tareaViewModel.update(tareaActualizada)
            },
            onItemLongClick = { tarea, view ->
                confirmarEliminacion(tarea, view)
            }
        )
        recyclerView.adapter = adapter

        tareaViewModel.tareas.observe(this) { listaActualizada ->
            adapter.actualizarLista(listaActualizada)
        }

        findViewById<View>(R.id.fabAgregarTarea).setOnClickListener {
            val intent = Intent(this, FormularioTareaActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.main).setOnLongClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            true
        }

        findViewById<View>(R.id.btnProbarNotificacion).setOnClickListener {
            val solicitud = OneTimeWorkRequestBuilder<RecordatorioTareasWorker>().build()
            WorkManager.getInstance(this).enqueue(solicitud)
            Toast.makeText(this, "Worker ejecutado, revisa las notificaciones", Toast.LENGTH_SHORT).show()
        }

        pedirPermisoNotificaciones()
    }

    private fun confirmarEliminacion(tarea: Tarea, view: View) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Eliminar tarea")
            .setMessage("¿Seguro que deseas eliminar \"${tarea.titulo}\"? Esta acción no se puede deshacer.")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Eliminar") { _, _ ->
                tareaViewModel.delete(tarea)
                Snackbar.make(view, "Tarea eliminada", Snackbar.LENGTH_LONG)
                    .setAction("Deshacer") {
                        tareaViewModel.insert(tarea)
                    }
                    .show()
            }
            .show()
    }

    private fun pedirPermisoNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}