package com.example.test7.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dbpii_proyecto.FinanzasGlobal
import com.example.dbpii_proyecto.Transaccion
import com.example.dbpii_proyecto.TransaccionesGlobales

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registrar(finanzas: FinanzasGlobal, navController: NavController) {
    var flujoExpanded by remember { mutableStateOf(false) }
    var flujoSeleccionado by remember { mutableStateOf("Seleccionar flujo") }

    var monedaSeleccionada by remember { mutableStateOf("S/.") }
    var monto by remember { mutableStateOf("") }

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    var tipoSeleccionado by remember { mutableStateOf("Empresa") }

    var ordinario by remember { mutableStateOf(false) }
    var aprobado by remember { mutableStateOf(false) }
    var emergencia by remember { mutableStateOf(false) }

    var mostrarDialogo by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(700.dp)
            .padding(16.dp)
            .border(2.dp, Color.Black)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            // Flujo Dropdown
            ExposedDropdownMenuBox(
                expanded = flujoExpanded,
                onExpandedChange = { flujoExpanded = !flujoExpanded }
            ) {
                TextField(
                    value = flujoSeleccionado,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Flujo") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = flujoExpanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = flujoExpanded,
                    onDismissRequest = { flujoExpanded = false }
                ) {
                    listOf("Ingreso", "Egreso").forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                flujoSeleccionado = opcion
                                flujoExpanded = false
                            }
                        )
                    }
                }
            }



            // Monto
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = monedaSeleccionada,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Moneda") },
                    modifier = Modifier.weight(1f)
                )
                TextField(
                    value = monto,
                    onValueChange = { monto = it },
                    label = { Text("Monto") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(2f)
                )
            }

            // Checks y Radio
            Row {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = ordinario, onCheckedChange = { ordinario = it })
                        Text("Ordinario", modifier = Modifier.padding(start = 4.dp))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = aprobado, onCheckedChange = { aprobado = it })
                        Text("Aprobado", modifier = Modifier.padding(start = 4.dp))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = emergencia, onCheckedChange = { emergencia = it })
                        Text("Emergencia", modifier = Modifier.padding(start = 4.dp))
                    }
                }

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = tipoSeleccionado == "Empresa",
                            onClick = { tipoSeleccionado = "Empresa" }
                        )
                        Text("Empresa")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = tipoSeleccionado == "Centro de estudios",
                            onClick = { tipoSeleccionado = "Centro de estudios" }
                        )
                        Text("Centro de estudios")
                    }
                }
            }

            // Título y descripción
            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Button(
                onClick = { mostrarDialogo = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Registrar")
            }
        }

        // Diálogo de confirmación
        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text("Confirmar registro") },
                text = { Text("¿Desea continuar con el registro, dado que no se puede editar?") },
                confirmButton = {
                    TextButton(onClick = {
                        mostrarDialogo = false

                        val montoFloat = monto.toFloatOrNull() ?: 0f

                        // Actualización financiera según flujo
                        if (flujoSeleccionado == "Ingreso") {
                            FinanzasGlobal.agregarGanancia(montoFloat)
                        } else if (flujoSeleccionado == "Egreso") {
                            FinanzasGlobal.agregarGasto(montoFloat)
                        }

                        // Agregar a lista
                        val nueva = Transaccion(
                            flujo = flujoSeleccionado,
                            monto = montoFloat,
                            titulo = titulo,
                            descripcion = descripcion,
                            tipoEntidad = tipoSeleccionado,
                            ordinario = ordinario,
                            aprobado = aprobado,
                            emergencia = emergencia
                        )
                        TransaccionesGlobales.agregarTransaccion(nueva)
                    }) {
                        Text("Ok")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarDialogo = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}


