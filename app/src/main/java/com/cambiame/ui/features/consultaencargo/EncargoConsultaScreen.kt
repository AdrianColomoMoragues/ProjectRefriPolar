package com.cambiame.ui.features.consultaencargo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cambiame.R
import com.cambiame.ui.features.EncargoUiState
import com.cambiame.ui.features.consultaencargo.componenets.Tabs
import com.cambiame.ui.features.consultaencargo.componenets.clienteEncargoCard
import com.cambiame.ui.features.consultaencargo.componenets.listCardInformation
import com.cambiame.ui.features.consultaencargo.componenets.listaTrabajadores

@Composable
fun EncargoConsultaScreen(
    encargoState: EncargoUiState,
) {
    val tabIndex = remember { mutableStateOf(0) }
    var percentage: Float = 0f
    if(encargoState.porcentaje.toFloatOrNull() != null){
        percentage = encargoState.porcentaje.toFloat()/100
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.casco),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(120.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                text = encargoState.nombre,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.manrope_semibold))
            )
        }
        if(encargoState.idCliente != null && encargoState.idEncargado != null) {
            clienteEncargoCard(
                cliente = encargoState.idCliente,
                encargado = encargoState.idEncargado
            )
        }
        Tabs(tabIndex = tabIndex)
        if(tabIndex.value == 0)
        {
            listCardInformation(
                priority = encargoState.prioridad,
                percentage = percentage,
                complete = encargoState.terminada)
        } else {
            listaTrabajadores(
                empleadosCollections = encargoState.empleadosCollection
            )
        }
    }
}



