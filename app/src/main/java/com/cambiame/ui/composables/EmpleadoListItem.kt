package com.cambiame.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cambiame.R
import com.cambiame.models.CategoriaProfesional
import com.cambiame.models.Empleado
import com.cambiame.ui.features.EmpleadoUiState
import com.cambiame.ui.features.toEmpleadoUiState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmpleadosListItem(
    modifier: Modifier = Modifier,
    empleadoSeleccionadoState: EmpleadoUiState?,
    empleadoState: Empleado,
    onEmpleadoClicked: (EmpleadoUiState) -> Unit,
    onConsultaClicked: () -> Unit,
    mostrarMenu: MutableState<Boolean>,
) = OutlinedCard(
    shape = RoundedCornerShape(0.dp),
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ),
    border = BorderStroke(0.dp, Color.Transparent),
    modifier = Modifier.combinedClickable(
        onClick = {
            if (empleadoSeleccionadoState != null) {
                if (empleadoSeleccionadoState.id != empleadoState.id) {
                    onEmpleadoClicked(empleadoState.toEmpleadoUiState())
                }
            } else {
                onEmpleadoClicked(empleadoState.toEmpleadoUiState())
            }
            onConsultaClicked()
        },
        onLongClick = {
            onEmpleadoClicked(empleadoState.toEmpleadoUiState())
        }
    )
) {
    Column {
        ContenidoCardEmpleado(
            empleadoState = empleadoState,
            empleadoSeleccionadoState = empleadoSeleccionadoState,
            mostrarMenu = mostrarMenu
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoCardEmpleado(
    modifier: Modifier = Modifier,
    empleadoSeleccionadoState: EmpleadoUiState?,
    empleadoState: Empleado,
    mostrarMenu: MutableState<Boolean>,
) {
    var colorState: Color = Color(android.graphics.Color.parseColor("#1CABE7"))
    if (empleadoSeleccionadoState != null) {
        if (empleadoSeleccionadoState.id == empleadoState.id) {
            colorState = Color(android.graphics.Color.parseColor("#073346"))
            mostrarMenu.value = true
        }
    } else {
        mostrarMenu.value = false
    }
    Row(
        modifier = Modifier
            .background(colorState)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FlowRow(horizontalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (empleadoState.imagen != null) {
                    AsyncImage(
                        model = empleadoState.imagen,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.imagen_sinfoto),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                }

            }

            DatosEmpleados(
                modifier = Modifier.padding(5.dp),
                nombre = empleadoState.nombre,
                apellido_1 = empleadoState.apellido1,
                apellido_2 = empleadoState.apellido2,
                antiguedad = empleadoState.antiguedad,
                reconocimientoMedico = empleadoState.reconocimientoMedico,
                categoriaProfesiona = empleadoState.codcategoriaProfesional
            )
        }
    }
}

@Composable
fun DatosEmpleados(
    modifier: Modifier = Modifier,
    nombre: String,
    apellido_1: String,
    apellido_2: String,
    antiguedad: Int,
    reconocimientoMedico: Boolean,
    categoriaProfesiona: CategoriaProfesional?,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "${apellido_1} ${apellido_2}, ${nombre}",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "${categoriaProfesiona!!.descripcion}",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        }
    }
}