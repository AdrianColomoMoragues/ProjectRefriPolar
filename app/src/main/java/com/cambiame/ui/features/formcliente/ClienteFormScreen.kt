package com.cambiame.ui.features.formcliente

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cambiame.R
import com.cambiame.ui.composables.OutlinedTexFieldLocalidad
import com.cambiame.ui.composables.OutlinedTextFieldCaracteristicas
import com.cambiame.ui.composables.OutlinedTextFieldDireccion
import com.cambiame.ui.composables.OutlinedTextFieldEmail
import com.cambiame.ui.composables.OutlinedTextFieldNombre
import com.cambiame.ui.composables.OutlinedTextFieldTelefono
import com.cambiame.ui.features.ClienteUiState

@Composable
fun ClienteFormScreen(
    editando: Boolean,
    clienteState: ClienteUiState,
    validacionContactoState: ValidacionClienteUiState,
    onClienteEvent: (ClienteFormEvent) -> Unit,
    onNavigateTrasFormCliente: (actualizaCliente: Boolean) -> Unit
) {
    Column (
        modifier = Modifier.then(Modifier.fillMaxSize()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = if (editando) "Editar Cliente" else "Nuevo Cliente",
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.manrope)),
            textAlign = TextAlign.Center
        )
        OutlinedTextFieldNombre(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = -5.dp),
            nombreState = clienteState.nombre,
            validacionState = validacionContactoState.validacionNombre,
            onValueChange = { onClienteEvent(ClienteFormEvent.OnChangeNombre(it)) }
        )
        OutlinedTextFieldTelefono(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = -10.dp),
            telefonoState = clienteState.telefono,
            validacionState = validacionContactoState.validacionTelefono,
            onValueChange = { onClienteEvent(ClienteFormEvent.OnChangeTelefono(it))}
        )
        OutlinedTextFieldEmail(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = -15.dp),
            emailState = clienteState.correo,
            validacionState = validacionContactoState.validacionCorreo,
            onValueChange = { onClienteEvent(ClienteFormEvent.OnChangeCorreo(it)) }
        )
        OutlinedTexFieldLocalidad(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = -20.dp),
            localidadState = clienteState.localidad,
            validacionState = validacionContactoState.validacionLocalidad,
            onValueChange = { onClienteEvent(ClienteFormEvent.OnChangeLocalidad(it)) }
        )
        OutlinedTextFieldDireccion(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = -25.dp),
            direccionState = clienteState.direccion,
            validacionState = validacionContactoState.validacionDireccion,
            onValueChange = { onClienteEvent(ClienteFormEvent.OnChangeDireccion(it)) }
        )
        OutlinedTextFieldCaracteristicas(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = -30.dp),
            caracteristicasState = clienteState.caracteristicas,
            validacionState = validacionContactoState.validacionCaracteristicas,
            onValueChange = { onClienteEvent(ClienteFormEvent.OnChangeCaracteristicas(it)) }
        )
        Row(
            modifier = Modifier.offset(y = -35.dp)
        ){
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { onClienteEvent(ClienteFormEvent.OnSaveCliente(onNavigateTrasFormCliente)) }
            ) {
                Text(text = "Enviar")
            }
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { onNavigateTrasFormCliente(false) }
            ) {
                Text(text = "Cancelar")
            }
        }
    }
}