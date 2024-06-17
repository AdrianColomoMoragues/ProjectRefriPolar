package com.cambiame.data

import com.cambiame.data.services.categoriaprofesional.CategoriaProfesionalApi
import com.cambiame.data.services.clientes.ClientesApi
import com.cambiame.data.services.empleados.EmpleadosApi
import com.cambiame.data.services.encargos.EncargosApi
import com.cambiame.models.CategoriaProfesional
import com.cambiame.models.Clientes
import com.cambiame.models.Empleado
import com.cambiame.models.Encargo

fun Clientes.toClienteApi() = ClientesApi(
    id = id,
    nombre = nombre,
    telefono = telefono,
    correo = correo,
    localidad = localidad,
    direccion = direccion,
    caracteristicas = caracteristicas
)

fun ClientesApi.toClientes() = Clientes(
    id = id,
    nombre = nombre,
    telefono = telefono,
    correo = correo,
    localidad = localidad,
    direccion = direccion,
    caracteristicas = caracteristicas
)

fun Empleado.toEmpleadosApi() = EmpleadosApi(
    id = id,
    nombre = nombre,
    apellido1 = apellido1,
    apellido2 = apellido2,
    codcategoriaProfesional = codcategoriaProfesional,
    antiguedad = antiguedad,
    reconocimientoMedico = reconocimientoMedico,
    imagen = imagen,
    telefono = telefono,
    mail = mail,
    direccion = direccion,
    ciudad = ciudad,
    provincia = provincia,
    cp = cp,
    salario = salario
)

fun EmpleadosApi.toEmpleado() = Empleado(
    id = id,
    nombre = nombre,
    apellido1 = apellido1,
    apellido2 = apellido2,
    codcategoriaProfesional = codcategoriaProfesional,
    antiguedad = antiguedad,
    reconocimientoMedico = reconocimientoMedico,
    imagen = imagen,
    telefono = telefono,
    mail = mail,
    direccion = direccion,
    ciudad = ciudad,
    provincia = provincia,
    cp = cp,
    salario = salario
)

fun Encargo.toEncargoApi() = EncargosApi(
    id = id,
    nombre = nombre,
    terminada = terminada,
    idEncargado = idEncargado,
    idCliente = idCliente,
    empleadosCollection = empleadosCollection,
    tipo = tipo,
    porcentaje = porcentaje,
    prioridad = prioridad
)

fun EncargosApi.toEncargo() = Encargo(
    id = id,
    nombre = nombre,
    terminada = terminada,
    idEncargado = idEncargado,
    idCliente = idCliente,
    empleadosCollection = empleadosCollection,
    tipo = tipo,
    prioridad = prioridad,
    porcentaje = porcentaje
)

fun CategoriaProfesional.toCategoriaProfesionalApi() = CategoriaProfesionalApi(
    codigo = codigo,
    descripcion = descripcion,
    encargo = descripcion
)

fun CategoriaProfesionalApi.toCategoriaProfesional() = CategoriaProfesional(
    codigo = codigo,
    descripcion = descripcion,
    encargo = descripcion
)