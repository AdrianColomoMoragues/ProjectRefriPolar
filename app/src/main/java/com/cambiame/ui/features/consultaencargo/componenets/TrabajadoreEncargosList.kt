package com.cambiame.ui.features.consultaencargo.componenets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cambiame.R
import com.cambiame.models.Empleado

@Composable
fun listaTrabajadores(
    empleadosCollections: List<Empleado>,
) {
    LazyColumn(
        modifier = Modifier
            .offset(y = -45.dp)
            .fillMaxHeight(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(empleadosCollections) { empleado ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(android.graphics.Color.parseColor("#1CABE7")),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp, 60.dp)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if(empleado.imagen != null) {
                            AsyncImage(
                                model = empleado.imagen,
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
                                    .size(60.dp)
                                    .clip(CircleShape)
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(1.dp),
                            text = "${empleado.apellido1} ${empleado.apellido2}, ${empleado.nombre}",
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.manrope_medium))
                        )
                        if (empleado.codcategoriaProfesional != null) {
                            Text(
                                modifier = Modifier
                                    .padding(1.dp),
                                text = empleado.codcategoriaProfesional.descripcion,
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.manrope_medium))
                            )
                        }
                    }
                }
            }
        }
    }
}