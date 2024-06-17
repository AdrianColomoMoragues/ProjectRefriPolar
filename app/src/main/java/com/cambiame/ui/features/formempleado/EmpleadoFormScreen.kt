package com.cambiame.ui.features.formempleado

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cambiame.R
import com.cambiame.models.CategoriaProfesional
import com.cambiame.ui.composables.OutlinedTextFieldAntiguedad
import com.cambiame.ui.composables.OutlinedTextFieldApellidos
import com.cambiame.ui.composables.OutlinedTextFieldCiudad
import com.cambiame.ui.composables.OutlinedTextFieldCodigoPostal
import com.cambiame.ui.composables.OutlinedTextFieldDireccion
import com.cambiame.ui.composables.OutlinedTextFieldEmail
import com.cambiame.ui.composables.OutlinedTextFieldNombre
import com.cambiame.ui.composables.OutlinedTextFieldProvincia
import com.cambiame.ui.composables.OutlinedTextFieldSalario
import com.cambiame.ui.composables.OutlinedTextFieldTelefono
import com.cambiame.ui.features.EmpleadoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmpleadoFormScreen(
    editando: Boolean,
    empleadoState: EmpleadoUiState,
    listaCategorias: List<CategoriaProfesional>,
    validacionEmpleadoUiState: ValidacionEmpleadoUiState,
    onEmpleadoFormEvent: (EmpleadoFormEvent) -> Unit,
    onNavigateTrasFormEmpleado: (actualizaEmpleado: Boolean) -> Unit,
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(listaCategorias[0]) }
    if (empleadoState.codcategoriaProfesional != null) {
        selectedText = empleadoState.codcategoriaProfesional
    }
    LazyColumn(
        modifier = Modifier.then(Modifier.fillMaxSize()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(1) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = if (editando) "Editar Empleado" else "Nuevo Empleado",
                fontSize = 35.sp,
                fontFamily = FontFamily(Font(R.font.manrope)),
                textAlign = TextAlign.Center
            )
            OutlinedTextFieldNombre(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -5.dp),
                nombreState = empleadoState.nombre,
                validacionState = validacionEmpleadoUiState.validacionNombre,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeNombre(it)) }
            )
            OutlinedTextFieldApellidos(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -15.dp),
                apellidosState = empleadoState.apellidos,
                validacionState = validacionEmpleadoUiState.validacionApellidos,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeApellidos(it)) }
            )
            OutlinedTextFieldEmail(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -25.dp),
                emailState = empleadoState.mail,
                validacionState = validacionEmpleadoUiState.validacionCorreo,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeMail(it)) }
            )
            OutlinedTextFieldTelefono(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -30.dp),
                telefonoState = empleadoState.telefono,
                validacionState = validacionEmpleadoUiState.validacionTelefono,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeTelefono(it)) }
            )
            OutlinedTextFieldDireccion(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -35.dp),
                direccionState = empleadoState.direccion,
                validacionState = validacionEmpleadoUiState.validacionDireccion,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeDireccion(it)) }
            )
            OutlinedTextFieldCiudad(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -40.dp),
                ciudadState = empleadoState.ciudad,
                validacionState = validacionEmpleadoUiState.validacionCiudad,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeCiudad(it)) }
            )
            OutlinedTextFieldProvincia(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -45.dp),
                provinciaState = empleadoState.provincia,
                validacionState = validacionEmpleadoUiState.validacionProvincia,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeProvincia(it)) }
            )
            OutlinedTextFieldCodigoPostal(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -50.dp),
                cpState = empleadoState.cp,
                validacionState = validacionEmpleadoUiState.validacionCodigoPostal,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeCodigoPostal(it)) }
            )
            OutlinedTextFieldSalario(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -55.dp),
                salarioState = empleadoState.salario,
                validacionState = validacionEmpleadoUiState.validacionSalario,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeSalario(it)) }
            )
            OutlinedTextFieldAntiguedad(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -60.dp),
                antiguedadState = empleadoState.antiguedad,
                validacionState = validacionEmpleadoUiState.validacionAntiguedad,
                onValueChange = { onEmpleadoFormEvent(EmpleadoFormEvent.OnChangeAntigueda(it)) }
            )
            Column (
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .offset(y = -60.dp),
            ){
                Text(
                    text = "Categoria profesional",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    OutlinedTextField(
                        value = selectedText.descripcion,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listaCategorias.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text =
                                if(item.codigo == "PEO" || item.codigo == "OTO") {
                                    "${item.descripcion} de Obra"
                                } else
                                if(item.codigo == "PER" || item.codigo == "OTR") {
                                    "${item.descripcion} de Reparación"
                                }
                                else "${item.descripcion}"
                                )},
                                onClick = {
                                    selectedText = item
                                    expanded = false
                                    onEmpleadoFormEvent(
                                        EmpleadoFormEvent.OnChangeCategoriaProfesional(
                                            item
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .width(300.dp)
                    .offset(y = -60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = empleadoState.reconocimientoMedico,
                    onCheckedChange = {
                        onEmpleadoFormEvent(
                            EmpleadoFormEvent.OnChangeReconocimientoMedico(it)
                        )
                    }
                )
                Text(text = "Reconocimiento medico")
            }
            Row (
                modifier = Modifier.offset(y = -65.dp)
            ) {
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        onEmpleadoFormEvent(
                            EmpleadoFormEvent.OnSaveEmpleado(
                                onNavigateTrasFormEmpleado
                            )
                        )
                    }
                ) {
                    Text(text = "Enviar")
                }
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = { onNavigateTrasFormEmpleado(false) }
                ) {
                    Text(text = "Cancelar")
                }
            }
        }
    }
}