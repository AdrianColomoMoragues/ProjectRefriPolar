package com.cambiame.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.pmdm.agenda.utilities.validacion.Validacion

@Composable
fun OutlinedTextFieldWithErrorState(
    modifier: Modifier = Modifier,
    label: String,
    textoState: String,
    textoPista: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(
        modifier = modifier,
        value = textoState,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = leadingIcon,
        placeholder = {
            Text(
                text = textoPista,
                style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
            )
        },
        label = { Text(if (validacionState.hayError) "${label}*" else label) },
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError
    )
}

@Composable
fun OutlinedTextFieldEmail(
    modifier: Modifier = Modifier,
    label: String = "Email",
    emailState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = emailState,
        textoPista = "ejemplo@correo.com",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTexFieldLocalidad(
    modifier: Modifier = Modifier,
    label: String = "Localidad",
    localidadState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = localidadState,
        textoPista = "Madrid",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldTelefono(
    modifier: Modifier = Modifier,
    label: String = "Telefono",
    telefonoState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = telefonoState,
        textoPista = "123456789",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldNombre(
    modifier: Modifier = Modifier,
    label: String = "Nombre",
    nombreState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = nombreState,
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldDireccion(
    modifier: Modifier = Modifier,
    label: String = "Direccion",
    direccionState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = direccionState,
        textoPista = "Calle ejemplo,Nº1",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldCaracteristicas(
    modifier: Modifier = Modifier,
    label: String = "Caracteristicas",
    caracteristicasState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = caracteristicasState,
        textoPista = "Supermercado de marcas blancas",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldApellidos(
    modifier: Modifier = Modifier,
    label: String = "Apellidos",
    apellidosState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = apellidosState,
        textoPista = "PrimerApellido SegundoApellido",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldAntiguedad(
    modifier: Modifier = Modifier,
    label: String = "Antigüedad",
    antiguedadState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = antiguedadState,
        textoPista = "2",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}


@Composable
fun OutlinedTextFieldPorcentaje(
    modifier: Modifier = Modifier,
    label: String = "Porcentaje",
    porcentajeState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = porcentajeState,
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldSalario(
    modifier: Modifier = Modifier,
    label: String = "Salario",
    salarioState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = salarioState,
        textoPista = "1250.75",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldCodigoPostal(
    modifier: Modifier = Modifier,
    label: String = "Codigo Postal",
    cpState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = cpState,
        textoPista = "00000",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}


@Composable
fun OutlinedTextFieldCiudad(
    modifier: Modifier = Modifier,
    label: String = "Ciudad",
    ciudadState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = ciudadState,
        textoPista = "Elche",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldProvincia(
    modifier: Modifier = Modifier,
    label: String = "Provincia",
    provinciaState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
){
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = provinciaState,
        textoPista = "Alicante",
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}