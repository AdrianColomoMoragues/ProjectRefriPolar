package com.cambiame.ui.features.consultaencargo.componenets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cambiame.R
import com.cambiame.models.Clientes
import com.cambiame.models.Empleado

@Composable
fun clienteEncargoCard(
    cliente: Clientes,
    encargado: Empleado
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#1CABE7")),
            contentColor = Color.White
        ),
        border = BorderStroke(0.dp, Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .offset(y = -15.dp)
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(0.50f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.client),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(
                            color = Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(55.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 3.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = cliente.nombre,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.manrope_medium))
                    )
                    Text(
                        text = cliente.telefono,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.manrope_medium))
                    )
                }
            }
            VerticalDivider(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 2.dp),
                thickness = 1.dp,
                color = Color.White
            )
            Row(
                modifier = Modifier
                    .weight(0.50f)
                    .offset(x = -8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(encargado.imagen != null) {
                    AsyncImage(
                        model = encargado.imagen,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        bitmap = ImageBitmap.imageResource(id = R.drawable.imagen_sinfoto),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(shape = RoundedCornerShape(25.dp))
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 1.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(0.dp),
                        text = "${encargado.apellido1} ${encargado.apellido2}, ${encargado.nombre}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.manrope_medium))

                    )
                    if(encargado.codcategoriaProfesional != null) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 0.dp),
                            text = encargado.codcategoriaProfesional.descripcion,
                            fontSize = 11.sp,
                            fontFamily = FontFamily(Font(R.font.manrope_medium))
                        )
                    }
                }
            }
        }
    }
}
