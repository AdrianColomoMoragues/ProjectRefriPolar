package com.cambiame.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Filter1
import androidx.compose.material.icons.filled.Filter2
import androidx.compose.material.icons.filled.Filter3
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cambiame.R
import com.cambiame.ui.features.consultacliente.ClienteConsultaViewModel
import com.cambiame.ui.features.consultaempleado.EmpleadoConsultaViewModel
import com.cambiame.ui.features.consultaencargo.EncargoConsultaViewModel
import com.cambiame.ui.features.formcliente.ClienteFormViewModel
import com.cambiame.ui.features.formempleado.EmpleadoFormViewModel
import com.cambiame.ui.features.formencargo.EncargoFormViewModel
import com.cambiame.ui.features.verclientes.ListaClientesEvents
import com.cambiame.ui.features.verclientes.ListaClientesViewModel
import com.cambiame.ui.features.verempleados.ListaEmpleadosEvents
import com.cambiame.ui.features.verempleados.ListaEmpleadosViewModel
import com.cambiame.ui.features.verencargos.ListaEncargosEvents
import com.cambiame.ui.features.verencargos.ListaEncargosViewModel
import com.cambiame.ui.navigation.ListaClienteGraphController
import com.cambiame.ui.navigation.ListaEmpleadosGraphRoute
import com.cambiame.ui.navigation.ListaEncargosGraphRoute
import com.cambiame.ui.navigation.RefriPolarNavHost
import com.cambiame.ui.navigation.navigateToEditarCliente
import com.cambiame.ui.navigation.navigateToEditarEmpleado
import com.cambiame.ui.navigation.navigateToEditarEncargo
import com.cambiame.ui.navigation.navigateToListaClientes
import com.cambiame.ui.navigation.navigateToListaEmpleados
import com.cambiame.ui.navigation.navigateToListaEncargos
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAppEnScaffoldDentroNavDrawer(
    dirr: String,
    onClickActionMenu: () -> Unit,
    comportamientoAnteScroll: TopAppBarScrollBehavior,
) = TopAppBar(
    title = {
        Text(
            text = when (dirr) {
                "lista_clientes" -> {
                    "Clientes"
                }
                "lista_empleados" -> {
                    "Empleados"
                }
                "lista_encargos" -> {
                    "Encargos"
                }
                else -> {
                    ""
                }
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    },
    navigationIcon = {
        IconButton(onClick = onClickActionMenu) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
        }
    },
    scrollBehavior = comportamientoAnteScroll,
)

enum class ItemMenuEjemploNavDrawer(
    val index: Int,
    val icono: ImageVector,
    val nombre: String,
    val ruta: String,
) {
    Pantalla1(
        index = 0,
        icono = Icons.Filled.Filter1,
        nombre = "Encargos",
        ruta = ListaEncargosGraphRoute
    ),
    Pantalla2(
        index = 1,
        icono = Icons.Filled.Filter2,
        nombre = "Empleados",
        ruta = ListaEmpleadosGraphRoute
    ),
    Pantalla3(
        index = 2,
        icono = Icons.Filled.Filter3,
        nombre = "Clientes",
        ruta = ListaClienteGraphController
    )
}

@Composable
fun ContenidoNavDrawer(
    modifier: Modifier = Modifier,
    selecteItemState: ItemMenuEjemploNavDrawer,
    onItemSelected: (ItemMenuEjemploNavDrawer) -> Unit,
    navController: NavHostController,
    vmListaClientes: ListaClientesViewModel,
    vmListaEmpleados: ListaEmpleadosViewModel,
    vmListaEncargos: ListaEncargosViewModel,
    mostrarMenu: MutableState<Boolean>,
) {
    val items = remember {
        listOf(
            ItemMenuEjemploNavDrawer.Pantalla1,
            ItemMenuEjemploNavDrawer.Pantalla2,
            ItemMenuEjemploNavDrawer.Pantalla3
        )
    }
    ModalDrawerSheet(
        drawerContainerColor = Color(android.graphics.Color.parseColor("#1CABE7")),
        drawerContentColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(height = 250.dp, width = 250.dp),
                bitmap = ImageBitmap.imageResource(id = R.drawable.copodenieve2_5_removebg_preview),
                contentDescription = null
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 10.dp),
            color = Color.White
        )
        Spacer(Modifier.height(12.dp))
        items.forEach { item ->
            NavigationDrawerItem(
                label = {
                    Text(
                        text = item.nombre,
                        color = Color.White
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(android.graphics.Color.parseColor("#46B8E9")),
                    unselectedContainerColor = Color(android.graphics.Color.parseColor("#1CABE7"))
                ),
                selected = item.index == selecteItemState.index,
                onClick = {
                    if (item.ruta.equals(ListaClienteGraphController)) {
                        vmListaClientes.deseleccionaCliente()
                    }
                    if (item.ruta.equals(ListaEmpleadosGraphRoute)) {
                        vmListaEmpleados.deseleccionarEmpleado()
                    }
                    if (item.ruta.equals(ListaEncargosGraphRoute)) {
                        vmListaEncargos.deseleccionaEncargo()
                    }
                    mostrarMenu.value = false
                    onItemSelected(item)
                    navController.navigate(item.ruta)
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Composable
fun PantallaConNavDrawer(
    vmListaClientes: ListaClientesViewModel,
    vmFormClientes: ClienteFormViewModel,
    vmConsultaCliente: ClienteConsultaViewModel,
    vmListaEmpleados: ListaEmpleadosViewModel,
    vmFormEmpleados: EmpleadoFormViewModel,
    vmConsultaEmpleados: EmpleadoConsultaViewModel,
    vmListaEncargos: ListaEncargosViewModel,
    vmFormEncargo: EncargoFormViewModel,
    vmConsultaEncargo: EncargoConsultaViewModel,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedItem by remember { mutableStateOf(ItemMenuEjemploNavDrawer.Pantalla1) }
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val onItemSelected: (ItemMenuEjemploNavDrawer) -> Unit = {
        scope.launch { drawerState.close() }
        selectedItem = it
    }
    val onClickActionMenu: () -> Unit = {
        scope.launch { drawerState.open() }
    }
    val mostrarMenu = remember { mutableStateOf(false) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ContenidoNavDrawer(
                selecteItemState = selectedItem,
                onItemSelected = onItemSelected,
                navController = navController,
                vmListaClientes = vmListaClientes,
                vmListaEmpleados = vmListaEmpleados,
                vmListaEncargos = vmListaEncargos,
                mostrarMenu = mostrarMenu
            )
        },
        content = {
            ScaffoldDentroNavDrawer(
                onClickActionMenu = onClickActionMenu,
                navController = navController,
                vmListaClientes = vmListaClientes,
                vmFormClientes = vmFormClientes,
                vmConsultaCliente = vmConsultaCliente,
                vmListaEmpleados = vmListaEmpleados,
                vmFormEmpleados = vmFormEmpleados,
                vmConsultaEmpleados = vmConsultaEmpleados,
                vmListaEncargos = vmListaEncargos,
                vmFormEncargo = vmFormEncargo,
                vmConsultaEncargo = vmConsultaEncargo,
                mostrarMenu = mostrarMenu
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDentroNavDrawer(
    navController: NavHostController,
    onClickActionMenu: () -> Unit,
    vmListaClientes: ListaClientesViewModel,
    vmFormClientes: ClienteFormViewModel,
    vmConsultaCliente: ClienteConsultaViewModel,
    vmListaEmpleados: ListaEmpleadosViewModel,
    vmFormEmpleados: EmpleadoFormViewModel,
    vmConsultaEmpleados: EmpleadoConsultaViewModel,
    vmListaEncargos: ListaEncargosViewModel,
    vmFormEncargo: EncargoFormViewModel,
    vmConsultaEncargo: EncargoConsultaViewModel,
    mostrarMenu: MutableState<Boolean>,
) {
    val comportamientoAnteScrollSup = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val comportamientoAnteScrollInf = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    val comportamientoAnteScroll = TopAppBarDefaults.pinnedScrollBehavior()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route


    Scaffold(
        modifier = Modifier
            .nestedScroll(comportamientoAnteScroll.nestedScrollConnection),
        topBar = {
            if (currentDestination != null) {
                when (currentDestination) {
                    "form_cliente/{idCliente}" -> {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Cliente"
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateToListaClientes() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                            scrollBehavior = comportamientoAnteScroll
                        )
                    }

                    "form_empleado/{idEmpleado}" -> {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Empleado",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateToListaEmpleados() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                            scrollBehavior = comportamientoAnteScroll
                        )
                    }

                    "form_encago/{idEmpleado}" -> {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Encargo",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateToListaEncargos() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                            scrollBehavior = comportamientoAnteScroll
                        )
                    }

                    "consulta_cliente/{idCliente}" -> {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Cliente",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateToListaClientes() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                            scrollBehavior = comportamientoAnteScroll
                        )
                    }

                    "consulta_empleado/{idEmpleado}" -> {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Empleado",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateToListaEmpleados() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                            scrollBehavior = comportamientoAnteScroll
                        )
                    }

                    "consulta_encargos/{idEncargo}" -> {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Encargos",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateToListaEncargos() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            },
                            scrollBehavior = comportamientoAnteScroll
                        )
                    }

                    else -> {
                        BarraAppEnScaffoldDentroNavDrawer(
                            dirr = currentDestination.toString(),
                            onClickActionMenu = onClickActionMenu,
                            comportamientoAnteScroll = comportamientoAnteScroll
                        )
                    }
                }
            }
        },
        bottomBar = {
            if (mostrarMenu.value) BarraAppInferiorSinSeleccion(
                navController = navController,
                comportamientoAnteScroll = comportamientoAnteScrollInf,
                vmListaClientes = vmListaClientes,
                vmListaEmpleados = vmListaEmpleados,
                vmListaEncargos = vmListaEncargos,
                onNavigateEditarCliente = { id ->
                    vmFormClientes.clearClienteState()
                    navController.navigateToEditarCliente(id)
                },
                onNavigateEditarEmpleado = { id ->
                    vmFormEmpleados.clearEmpleadoState()
                    navController.navigateToEditarEmpleado(id)

                },
                onNavigateEditarEncargo = { id ->
                    vmFormEncargo.clearEncargoState()
                    navController.navigateToEditarEncargo(id)
                },
            )
        },
        content = { innerPadding ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                navController = navController,
                mostrarMenu = mostrarMenu,
                vmListaClientes = vmListaClientes,
                vmFormClientes = vmFormClientes,
                vmConsultaCliente = vmConsultaCliente,
                vmListaEmpleados = vmListaEmpleados,
                vmFormEmpleados = vmFormEmpleados,
                vmConsultaEmpleados = vmConsultaEmpleados,
                vmListaEncargos = vmListaEncargos,
                vmFormEncargo = vmFormEncargo,
                vmConsultaEncargo = vmConsultaEncargo
            )

        }
    )
}

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mostrarMenu: MutableState<Boolean>,
    vmListaClientes: ListaClientesViewModel,
    vmFormClientes: ClienteFormViewModel,
    vmConsultaCliente: ClienteConsultaViewModel,
    vmListaEmpleados: ListaEmpleadosViewModel,
    vmFormEmpleados: EmpleadoFormViewModel,
    vmConsultaEmpleados: EmpleadoConsultaViewModel,
    vmListaEncargos: ListaEncargosViewModel,
    vmFormEncargo: EncargoFormViewModel,
    vmConsultaEncargo: EncargoConsultaViewModel,
) {
    Box(modifier = modifier) {
        RefriPolarNavHost(
            navController = navController,
            mostrarMenu = mostrarMenu,
            vmListaClientes = vmListaClientes,
            vmFormClientes = vmFormClientes,
            vmConsultaCliente = vmConsultaCliente,
            vmListaEmpleados = vmListaEmpleados,
            vmFormEmpleados = vmFormEmpleados,
            vmConsultaEmpleados = vmConsultaEmpleados,
            vmListaEncargos = vmListaEncargos,
            vmFormEncargo = vmFormEncargo,
            vmConsultaEncargo = vmConsultaEncargo
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraAppInferiorSinSeleccion(
    comportamientoAnteScroll: BottomAppBarScrollBehavior
    = BottomAppBarDefaults.exitAlwaysScrollBehavior(),
    navController: NavHostController,
    vmListaClientes: ListaClientesViewModel,
    vmListaEmpleados: ListaEmpleadosViewModel,
    vmListaEncargos: ListaEncargosViewModel,
    onNavigateEditarCliente: (id: Int) -> Unit,
    onNavigateEditarEmpleado: (id: Int) -> Unit,
    onNavigateEditarEncargo: (id: Int) -> Unit,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    val descripcionEIconos = remember {
        listOf(
            "Delete Items" to Icons.Filled.Delete
        )
    }
    BottomAppBar(
        actions = {
            descripcionEIconos.forEach { (descripcion, icono) ->
                IconButton(
                    onClick = {
                        if (currentDestination.toString().equals("lista_empleados")) {
                            vmListaEmpleados.onListaEmpleados(ListaEmpleadosEvents.OnDeleteEmpleado)
                        }
                        if (currentDestination.toString().equals("lista_encargos")) {
                            vmListaEncargos.onListaEncargosEvent(ListaEncargosEvents.OnDeleteEncargo)
                        }
                        if (currentDestination.toString().equals("lista_clientes")) {
                            vmListaClientes.onListaClienteEvent(ListaClientesEvents.OnDeleteCliente)
                        }
                    }) {
                    Icon(
                        imageVector = icono,
                        tint = MaterialTheme.colorScheme.secondary,
                        contentDescription = descripcion
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (currentDestination.toString().equals("lista_empleados")) {
                        vmListaEmpleados.onListaEmpleados(
                            ListaEmpleadosEvents.OnEditEmpleado(
                                onNavigateEditarEmpleado
                            )
                        )
                        vmListaEmpleados.deseleccionarEmpleado()
                    }
                    if (currentDestination.toString().equals("lista_encargos")) {
                        vmListaEncargos.onListaEncargosEvent(
                            ListaEncargosEvents.OnEditEncargo(
                                onNavigateEditarEncargo
                            )
                        )
                        vmListaEncargos.deseleccionaEncargo()
                    }
                    if (currentDestination.toString().equals("lista_clientes")) {
                        vmListaClientes.onListaClienteEvent(
                            ListaClientesEvents.OnEditCliente(
                                onNavigateEditarCliente
                            )
                        )
                        vmListaClientes.deseleccionaCliente()
                    }
                },
                containerColor = BottomAppBarDefaults.containerColor,
                contentColor = MaterialTheme.colorScheme.secondary,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Edit, "Edit item")
            }
        },
        scrollBehavior = comportamientoAnteScroll
    )
}