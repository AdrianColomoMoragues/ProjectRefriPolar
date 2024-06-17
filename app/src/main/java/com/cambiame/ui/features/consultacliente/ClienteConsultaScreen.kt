package com.cambiame.ui.features.consultacliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cambiame.R
import com.cambiame.ui.features.ClienteUiState

@Composable
fun ClienteConsultaScreen(
    clienteState: ClienteUiState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(290.dp)
                .fillMaxWidth()
        ){
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.fondoempleado),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .offset(y = -5.dp)
                    .clip(shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.client),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(color = Color.Black)
                )
                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = clienteState.nombre,
                        fontFamily = FontFamily(Font(R.font.manrope_bold)),
                        color = Color.White
                    )
                    Text(
                        text = clienteState.correo,
                        fontFamily = FontFamily(Font(R.font.manrope_medium)),
                        color = Color.White
                    )
                }
            }
        }
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color(android.graphics.Color.parseColor("#073346")),
                contentColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .width(350.dp)
                .offset(y = -50.dp)
        ) {
            Row(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "Caracteristicas: ",
                    modifier = Modifier
                        .padding(5.dp),
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                Text(
                    text = "${clienteState.caracteristicas}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(Font(R.font.manrope_medium))
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Row(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "Telefono: ",
                    modifier = Modifier
                        .padding(5.dp),
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                Text(
                    text = "${clienteState.telefono}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(Font(R.font.manrope_medium))
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Row(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "Direccion: ",
                    modifier = Modifier
                        .padding(5.dp),
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                Text(
                    text = "${clienteState.direccion}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(Font(R.font.manrope_medium))
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Row(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "Localidad: ",
                    modifier = Modifier
                        .padding(5.dp),
                    fontFamily = FontFamily(Font(R.font.manrope_semibold))
                )
                Text(
                    text = "${clienteState.localidad}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(Font(R.font.manrope_medium))
                )
            }
        }

    }
}