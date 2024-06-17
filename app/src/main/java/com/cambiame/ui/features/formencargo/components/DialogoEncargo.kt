package com.cambiame.ui.features.formencargo.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import com.cambiame.models.Empleado
import com.cambiame.ui.features.formencargo.EncargoFormEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoEquipo(
    tipo: String,
    idEncargado: Int,
    listaActual: List<Empleado>,
    empleadosLista: List<Empleado>,
    onEncargoEvent: (EncargoFormEvent) -> Unit,
    openDialog: MutableState<Boolean>
) {
    var firstIndex: Int = 0
    var expandedEmpleado by remember { mutableStateOf(false) }
    for (empleado in empleadosLista){
        if(listaActual.contains(empleado) && empleadosLista.indexOf(empleado) == firstIndex)
        {
            firstIndex++
        }
    }
    var selectTextEmpleado by remember { mutableStateOf(empleadosLista[firstIndex]) }
    AlertDialog(
        icon = {  },
        title = {
            Text("Añadir empleado")
        },
        text = {
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .padding(8.dp),
                expanded = expandedEmpleado,
                onExpandedChange = {
                    expandedEmpleado = !expandedEmpleado
                }
            ) {
                OutlinedTextField(
                    value = "${selectTextEmpleado.apellido2} ${selectTextEmpleado.apellido1}, ${selectTextEmpleado.nombre}",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedEmpleado) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedEmpleado,
                    onDismissRequest = { expandedEmpleado = false }
                ) {
                    empleadosLista.forEach { item ->
                        if(item.codcategoriaProfesional != null) {
                            if (!listaActual.contains(item) && tipo.toLowerCase() == item.codcategoriaProfesional.encargo.toLowerCase() && idEncargado != item.id) {
                                DropdownMenuItem(
                                    text = { Text(text = "${item.apellido1} ${item.apellido2}, ${item.nombre} \n${item.codcategoriaProfesional!!.descripcion} ${item.codcategoriaProfesional.encargo}") },
                                    onClick = {
                                        selectTextEmpleado = item
                                        expandedEmpleado = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onEncargoEvent(EncargoFormEvent.OnAddEmpleado(selectTextEmpleado))
                    openDialog.value = false
                }
            ) {
                Text(text = "Confirm")
            }
        },
        onDismissRequest = {
            openDialog.value = false
        },
        dismissButton = {
            TextButton(
                onClick = { openDialog.value = false}
            ) {
                Text(text = "Dismiss")
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBoxAddEmpleado(){
    val listaEmpleados = listOf<Empleado>()
    var expandedEmpleado by remember { mutableStateOf(false) }
    var selectTextEmpleado by remember { mutableStateOf(listaEmpleados[0]) }
    ExposedDropdownMenuBox(
        modifier = Modifier
            .padding(8.dp),
        expanded = expandedEmpleado,
        onExpandedChange = {
            expandedEmpleado = !expandedEmpleado
        }
    ) {
        OutlinedTextField(
            value = "${selectTextEmpleado.apellido2} ${selectTextEmpleado.apellido2}, ${selectTextEmpleado.nombre} \n${selectTextEmpleado.codcategoriaProfesional!!.descripcion} ${selectTextEmpleado.codcategoriaProfesional!!.descripcion}",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedEmpleado) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expandedEmpleado,
            onDismissRequest = { expandedEmpleado = false }
        ) {
            listaEmpleados.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.nombre) },
                    onClick = {
                        selectTextEmpleado = item
                        expandedEmpleado = false

                    }
                )
            }
        }
    }
}