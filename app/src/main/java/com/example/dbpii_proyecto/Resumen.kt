package com.example.dbpii_proyecto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class ResumenFinanciero(
    var ganancia: Float = 0f,
    var gastos: Float = 0f,
    var disponible: Float = 0f,
    var balance: Float = 0f
)

object FinanzasGlobal {
    var resumen by mutableStateOf(ResumenFinanciero())

    fun agregarGanancia(monto: Float) {
        resumen = resumen.copy(
            ganancia = resumen.ganancia + monto,
            disponible = resumen.disponible + monto,
            balance = resumen.balance + monto
        )
    }

    fun agregarGasto(monto: Float) {
        resumen = resumen.copy(
            gastos = resumen.gastos + monto,
            disponible = resumen.disponible - monto,
            balance = resumen.balance - monto
        )
    }
}

