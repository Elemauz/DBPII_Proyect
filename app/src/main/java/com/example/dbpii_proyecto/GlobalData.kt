package com.example.dbpii_proyecto

import androidx.compose.runtime.mutableStateListOf

data class Transaccion(
    val flujo: String,
    val monto: Float,
    val titulo: String,
    val descripcion: String,
    val tipoEntidad: String, // empresa o estudios
    val ordinario: Boolean,
    val aprobado: Boolean,
    val emergencia: Boolean
)
object TransaccionesGlobales {
    val lista = mutableStateListOf<Transaccion>()

    fun agregarTransaccion(transaccion: Transaccion) {
        lista.add(transaccion)
    }

    fun obtenerTransacciones(): List<Transaccion> {
        return lista
    }
}
