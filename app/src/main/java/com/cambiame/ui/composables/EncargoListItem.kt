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
import com.cambiame.R
import com.cambiame.models.Clientes
import com.cambiame.models.Empleado
import com.cambiame.models.Encargo
import com.cambiame.ui.features.EncargoUiState
import com.cambiame.ui.features.toEncargoUiState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EncargosListItem(
    modifier: Modifier = Modifier,
    encargoState: Encargo,
    onContactoClicked: (EncargoUiState) -> Unit,
    encargoSeleccionadoState: EncargoUiState?,
    onCosultaClicked: () -> Unit,
    mostrarMenu: MutableState<Boolean>,
) = OutlinedCard(
    shape = RoundedCornerShape(0.dp),
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ),
    border = BorderStroke(0.dp, Color.Transparent),
    modifier = Modifier
        .combinedClickable(
            onClick = {
                if (encargoSeleccionadoState != null) {
                    if (encargoSeleccionadoState.id != encargoState.id) {
                        onContactoClicked(encargoState.toEncargoUiState())
                    }
                } else {
                    onContactoClicked(encargoState.toEncargoUiState())
                }
                onCosultaClicked()
            },
            onLongClick = {
                onContactoClicked(encargoState.toEncargoUiState())
            }
        )

) {
    Column {
        ContenidoCardEncargo(
            encargoSeleccionadoState = encargoSeleccionadoState,
            encargoState = encargoState,
            mostrarMenu = mostrarMenu
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoCardEncargo(
    modifier: Modifier = Modifier,
    encargoSeleccionadoState: EncargoUiState?,
    encargoState: Encargo,
    mostrarMenu: MutableState<Boolean>,
) {
    var colorState: Color = Color(android.graphics.Color.parseColor("#1CABE7"))
    if (encargoSeleccionadoState != null) {
        if (encargoSeleccionadoState.id == encargoState.id) {
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
        FlowRow {
            Box(
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (encargoState.tipo != null) {
                    if (encargoState.tipo == "Obra") {
                        Image(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.casco),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Image(
                            bitmap = ImageBitmap.imageResource(id = R.drawable.reparacion),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }
            DatosEncargos(
                modifier = Modifier.padding(5.dp),
                nombre = encargoState.nombre,
                tipo = encargoState.tipo,
                cliente = encargoState.idCliente,
                encargado = encargoState.idEncargado,
                terminado = encargoState.terminada,
                empleados = encargoState.empleadosCollection
            )
        }
    }
}


@Composable
fun DatosEncargos(
    modifier: Modifier = Modifier,
    nombre: String,
    tipo: String,
    cliente: Clientes?,
    encargado: Empleado?,
    terminado: Boolean,
    empleados: List<Empleado>,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "${nombre}",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "${cliente!!.nombre}",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        }
    }
}