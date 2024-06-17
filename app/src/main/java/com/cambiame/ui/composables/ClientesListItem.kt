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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cambiame.R
import com.cambiame.models.Clientes
import com.cambiame.ui.features.ClienteUiState
import com.cambiame.ui.features.toClienteUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClienteListItem(
    modifier: Modifier = Modifier,
    clienteState: Clientes,
    clienteSeleccionadoState: ClienteUiState?,
    onClienteClicked: (ClienteUiState) -> Unit,
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
            if (clienteSeleccionadoState != null) {
                if (clienteSeleccionadoState.id != clienteState.id) {
                    onClienteClicked(clienteState.toClienteUiState())
                }
            } else {
                onClienteClicked(clienteState.toClienteUiState())
            }
            onConsultaClicked()
        },
        onLongClick = {
            onClienteClicked(clienteState.toClienteUiState())
        }
    )
) {
    Column {
        ContenidoCardCliente(
            clienteState = clienteState,
            clienteSeleccionadoState = clienteSeleccionadoState,
            mostrarMenu = mostrarMenu
        )
    }
}

@Composable
fun ContenidoCardCliente(
    clienteState: Clientes,
    clienteSeleccionadoState: ClienteUiState?,
    modifier: Modifier = Modifier,
    mostrarMenu: MutableState<Boolean>,
) {
    var colorState: Color = Color(android.graphics.Color.parseColor("#1CABE7"))
    if (clienteSeleccionadoState != null) {
        if (clienteSeleccionadoState.id == clienteState.id) {
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
        DatosClientes(
            nombre = clienteState.nombre,
            correo = clienteState.correo,
            telefono = clienteState.telefono,
            direccion = clienteState.direccion,
            localidad = clienteState.localidad,
            caracteristicas = clienteState.caracteristicas
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DatosClientes(
    modifier: Modifier = Modifier,
    nombre: String,
    correo: String,
    telefono: String,
    direccion: String,
    localidad: String,
    caracteristicas: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        FlowRow(horizontalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                modifier = Modifier.padding(5.dp),
                text = "${nombre}",
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
            )
        }
        FlowRow(horizontalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.phone_white),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                modifier = Modifier.padding(5.dp),
                text = "+34 ${telefono}",
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        }
    }
}