package com.cambiame.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.cambiame.ui.features.PantallaConNavDrawer
import com.cambiame.ui.features.consultacliente.ClienteConsultaViewModel
import com.cambiame.ui.features.consultaempleado.EmpleadoConsultaViewModel
import com.cambiame.ui.features.consultaencargo.EncargoConsultaViewModel
import com.cambiame.ui.features.formcliente.ClienteFormViewModel
import com.cambiame.ui.features.formempleado.EmpleadoFormViewModel
import com.cambiame.ui.features.formencargo.EncargoFormViewModel
import com.cambiame.ui.features.verclientes.ListaClientesViewModel
import com.cambiame.ui.features.verempleados.ListaEmpleadosViewModel
import com.cambiame.ui.features.verencargos.ListaEncargosViewModel
import com.cambiame.ui.theme.ProyectoBaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    val vmListaClientes = hiltViewModel<ListaClientesViewModel>()
                    val vmFormClientes = hiltViewModel<ClienteFormViewModel>()
                    val vmConsultaCliente = hiltViewModel<ClienteConsultaViewModel>()
                    val vmListaEmpleados = hiltViewModel<ListaEmpleadosViewModel>()
                    val vmFormEmpleados = hiltViewModel<EmpleadoFormViewModel>()
                    val vmConsultaEmpleados = hiltViewModel<EmpleadoConsultaViewModel>()
                    val vmListaEncargos = hiltViewModel<ListaEncargosViewModel>()
                    val vmFormEncargo = hiltViewModel<EncargoFormViewModel>()
                    val vmConsultaEncargo = hiltViewModel<EncargoConsultaViewModel>()
                    PantallaConNavDrawer(
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
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProyectoBaseTheme {
        Greeting("Android")
    }
}