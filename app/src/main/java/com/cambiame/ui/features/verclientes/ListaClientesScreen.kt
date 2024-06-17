package com.cambiame.ui.features.verclientes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cambiame.models.Clientes
import com.cambiame.ui.composables.ClienteListItem
import com.cambiame.ui.features.ClienteUiState

@Composable
fun ListaClientesScreen(
    modifier: Modifier = Modifier,
    clientesState: List<Clientes>,
    clienteSeleccionadoState: ClienteUiState?,
    onContactoClicked: (ClienteUiState) -> Unit,
    onAddClicked: () -> Unit,
    onCosultaClicked: () -> Unit,
    mostrarMenu: MutableState<Boolean>
) {
    Box(modifier = modifier.then(Modifier.fillMaxSize())) {
        LazyColumn(
            contentPadding = PaddingValues(all = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(clientesState) { cliente ->
                ClienteListItem(
                    clienteState = cliente,
                    onClienteClicked = onContactoClicked,
                    onConsultaClicked = onCosultaClicked,
                    clienteSeleccionadoState = clienteSeleccionadoState,
                    mostrarMenu = mostrarMenu
                )
            }
        }
        if(!mostrarMenu.value) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                containerColor = Color(android.graphics.Color.parseColor("#1D5D79")),
                contentColor = androidx.compose.ui.graphics.Color.White,
                shape = CircleShape,
                onClick = { onAddClicked() }) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    }
}