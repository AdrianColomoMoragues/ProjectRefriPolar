package com.cambiame.ui.features.formencargo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cambiame.R
import com.cambiame.models.Clientes
import com.cambiame.models.Empleado
import com.cambiame.ui.composables.OutlinedTextFieldNombre
import com.cambiame.ui.composables.OutlinedTextFieldPorcentaje
import com.cambiame.ui.features.EmpleadoUiState
import com.cambiame.ui.features.EncargoUiState
import com.cambiame.ui.features.formencargo.components.DialogoEquipo
import com.cambiame.ui.features.toEmpleado

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EncargoFormScreen(
    editando: Boolean,
    encargoState: EncargoUiState,
    empleadoState: EmpleadoUiState?,
    validacionContactoState: ValidacionEncargoUiState,
    listaEmpleados: List<Empleado>,
    listaClientes: List<Clientes>,
    onEncargoEvent: (EncargoFormEvent) -> Unit,
    onNavigateTrasFormEncargo: (actualizaEncargo: Boolean) -> Unit,
) {
    val listaTipos = listOf("Obra", "Reparacion")
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    var expandedCliente by remember { mutableStateOf(false) }
    var expandedEncargado by remember { mutableStateOf(false) }
    var expandedPrioridad by remember { mutableStateOf(false) }
    var expandedTipo by remember { mutableStateOf(false) }
    var selectTextEmpleado by remember { mutableStateOf(listaEmpleados[0]) }
    var selectTextCliente by remember { mutableStateOf(listaClientes[0]) }
    var selectPrioridad by remember { mutableStateOf(1) }
    var selectTipo by remember { mutableStateOf(listaTipos[0]) }
    val prioridades = listOf(1, 2, 3)
    if (encargoState.idEncargado != null) {
        selectTextEmpleado = encargoState.idEncargado
    }
    if (encargoState.idCliente != null) {
        selectTextCliente = encargoState.idCliente
    }
    selectPrioridad = encargoState.prioridad
    LazyColumn(
        modifier = Modifier.then(Modifier.fillMaxSize()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(1) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = if (editando) "Editar Encargo" else "Nuevo Encargo",
                fontSize = 35.sp,
                fontFamily = FontFamily(Font(R.font.manrope)),
                textAlign = TextAlign.Center
            )
            OutlinedTextFieldNombre(
                modifier = Modifier
                    .padding(8.dp)
                    .offset(y = -10.dp),
                nombreState = encargoState.nombre,
                validacionState = validacionContactoState.validacionNombre,
                onValueChange = { onEncargoEvent(EncargoFormEvent.OnChangeNombre(it)) }
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .offset(y = -30.dp),
            ) {
                Text(
                    text = "Cliente",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                ExposedDropdownMenuBox(
                    expanded = expandedCliente,
                    onExpandedChange = {
                        expandedCliente = !expandedCliente
                    }
                ) {
                    OutlinedTextField(
                        value = selectTextCliente.nombre,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCliente) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedCliente,
                        onDismissRequest = { expandedCliente = false }
                    ) {
                        listaClientes.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.nombre) },
                                onClick = {
                                    selectTextCliente = item
                                    expandedCliente = false
                                    onEncargoEvent(EncargoFormEvent.OnChangeCliente(item))
                                }
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .offset(y = -35.dp)
            ) {
                Text(
                    text = "Encargado",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                ExposedDropdownMenuBox(
                    expanded = expandedEncargado,
                    onExpandedChange = {
                        expandedEncargado = !expandedEncargado
                    }
                ) {
                    OutlinedTextField(
                        value = selectTextEmpleado.nombre,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedEncargado) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedEncargado,
                        onDismissRequest = { expandedEncargado = false }
                    ) {
                        listaEmpleados.forEach { item ->
                            if(item.codcategoriaProfesional!!.descripcion.toLowerCase() != "peon" && item.codcategoriaProfesional.encargo.toLowerCase() == encargoState.tipo.toLowerCase()) {
                                DropdownMenuItem(
                                    text = { Text(text = item.nombre) },
                                    onClick = {
                                        selectTextEmpleado = item
                                        expandedEncargado = false
                                        onEncargoEvent(EncargoFormEvent.OnChangeEncargado(item))
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .offset(y = -35.dp),
            ) {
                Text(
                    text = "Tipo",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                ExposedDropdownMenuBox(
                    expanded = expandedTipo,
                    onExpandedChange = {
                        expandedTipo = !expandedTipo
                    }
                ) {
                    OutlinedTextField(
                        value = selectTipo,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTipo) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedTipo,
                        onDismissRequest = { expandedTipo = false }
                    ) {
                        listaTipos.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectTipo = item
                                    expandedCliente = false
                                    onEncargoEvent(EncargoFormEvent.OnChangeTipo(item))
                                }
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .offset(y = -40.dp),
            ) {
                Text(
                    text = "Prioridad",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                ExposedDropdownMenuBox(
                    expanded = expandedPrioridad,
                    onExpandedChange = {
                        expandedPrioridad = !expandedPrioridad
                    }
                ) {
                    OutlinedTextField(
                        value = selectPrioridad.toString(),
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPrioridad) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedPrioridad,
                        onDismissRequest = { expandedPrioridad = false }
                    ) {
                        prioridades.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.toString()) },
                                onClick = {
                                    selectPrioridad = item
                                    expandedPrioridad = false
                                    onEncargoEvent(EncargoFormEvent.OnChangePrioridad(item))
                                }
                            )
                        }
                    }
                }
            }
            OutlinedTextFieldPorcentaje(
                modifier = Modifier
                    .padding(8.dp)
                    .offset(y = -45.dp),
                porcentajeState = encargoState.porcentaje,
                validacionState = validacionContactoState.validacionPorcentaje,
                onValueChange = { onEncargoEvent(EncargoFormEvent.OnChangePorcentaje(it)) }
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .offset(y = -65.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    FloatingActionButton(
                        modifier = Modifier
                            .size(width = 50.dp, height = 50.dp),
                        containerColor = Color(android.graphics.Color.parseColor("#1CABE7")),
                        contentColor = Color.White,
                        onClick = { openDialog.value = true }
                    ) {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = Icons.Filled.Add,
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.size(width = 20.dp, height = 55.dp))
                    if (empleadoState != null) {
                        FloatingActionButton(
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp),
                            containerColor = Color(android.graphics.Color.parseColor("#1CABE7")),
                            contentColor = Color.White,
                            onClick = {
                                onEncargoEvent(EncargoFormEvent.OnDeleteEmpleado(empleadoState!!.toEmpleado()))
                            }
                        ) {
                            Icon(Icons.Filled.Delete, "Floating action button.")
                        }
                    }
                }
                if (openDialog.value) {
                    DialogoEquipo(
                        idEncargado = encargoState.idEncargado!!.id,
                        tipo = encargoState.tipo,
                        listaActual = encargoState.empleadosCollection,
                        empleadosLista = listaEmpleados,
                        onEncargoEvent = onEncargoEvent,
                        openDialog = openDialog
                    )
                }
                LazyColumn(
                    contentPadding = PaddingValues(all = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .heightIn(0.dp, 350.dp)
                        .width(290.dp)
                ) {
                    items(encargoState.empleadosCollection) { empleado ->
                        var colorState: Color = Color(android.graphics.Color.parseColor("#1CABE7"))
                        if (empleadoState != null) {
                            if (empleadoState.id == empleado.id) {
                                colorState = Color(android.graphics.Color.parseColor("#073346"))
                            }
                        }
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = colorState,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .combinedClickable(
                                    onClick = {
                                        onEncargoEvent(EncargoFormEvent.OnSelectEmpleado(empleado))
                                    },
                                    onLongClick = {
                                        onEncargoEvent(EncargoFormEvent.OnSelectEmpleado(empleado))
                                    }
                                )

                        ) {
                            Row(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp, 60.dp)
                                        .padding(8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (empleado.imagen != null) {
                                        AsyncImage(
                                            model = empleado.imagen,
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(55.dp)
                                                .clip(CircleShape)
                                        )
                                    } else {
                                        Image(
                                            bitmap = ImageBitmap.imageResource(id = R.drawable.imagen_sinfoto),
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(60.dp)
                                                .clip(CircleShape)
                                        )
                                    }
                                }
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(1.dp),
                                        text = "${empleado.apellido1} ${empleado.apellido2}, ${empleado.nombre}",
                                        color = Color.White,
                                        fontFamily = FontFamily(Font(R.font.manrope_medium))
                                    )
                                    if (empleado.codcategoriaProfesional != null) {
                                        Text(
                                            modifier = Modifier
                                                .padding(1.dp),
                                            text = empleado.codcategoriaProfesional.descripcion,
                                            color = Color.White,
                                            fontFamily = FontFamily(Font(R.font.manrope_medium))
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.offset(y = 0.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .padding(5.dp),
                        onClick = {
                            onEncargoEvent(
                                EncargoFormEvent.OnSaveCliente(
                                    onNavigateTrasFormEncargo
                                )
                            )
                        }
                    ) {
                        Text(text = "Enviar")
                    }
                    Button(
                        modifier = Modifier
                            .padding(5.dp),
                        onClick = {
                            onNavigateTrasFormEncargo(false)
                        }
                    ) {
                        Text(text = "Cancelar")
                    }
                }
            }

        }
    }
}




