package com.metodologiaDefaz.listadetareas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(
    private var tareas: List<Tarea>,
    private val onItemClick: (Tarea) -> Unit,
    private val onCheckboxClick: (Tarea) -> Unit,
    private val onItemLongClick: (Tarea, View) -> Unit
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    class TareaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.tvTitulo)
        val categoria: TextView = view.findViewById(R.id.tvCategoria)
        val fechaLimite: TextView = view.findViewById(R.id.tvFechaLimite)
        val checkbox: CheckBox = view.findViewById(R.id.checkboxCompletada)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.titulo.text = tarea.titulo
        holder.fechaLimite.text = tarea.fechaLimite
        holder.checkbox.isChecked = tarea.completada

        holder.itemView.setOnClickListener {
            onItemClick(tarea)
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClick(tarea, holder.itemView)
            true
        }

        holder.checkbox.setOnClickListener {
            onCheckboxClick(tarea)
        }
    }

    override fun getItemCount() = tareas.size

    fun actualizarLista(nuevaLista: List<Tarea>) {
        tareas = nuevaLista
        notifyDataSetChanged()
    }
}