package com.cambiame.ui.features.formcliente

sealed interface ClienteFormEvent {
    data class OnChangeNombre(val nombre: String) : ClienteFormEvent
    data class OnChangeTelefono(val telefono: String) : ClienteFormEvent
    data class OnChangeCorreo(val correo: String) : ClienteFormEvent
    data class OnChangeLocalidad(val localidad: String) : ClienteFormEvent
    data class OnChangeDireccion(val direccion: String) : ClienteFormEvent
    data class OnChangeCaracteristicas(val caracteristicas: String) : ClienteFormEvent
    data class OnSaveCliente(val onNavigateTrasFormCliente: (actualizaCliente : Boolean) -> Unit) : ClienteFormEvent
    data object OnDismissError : ClienteFormEvent
}