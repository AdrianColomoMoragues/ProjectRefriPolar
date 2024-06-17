package com.cambiame.ui.features.consultaencargo.componenets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cambiame.R

@Composable
fun listCardInformation(
    priority: Int,
    percentage: Float,
    complete: Boolean,
) {
    var colorPriority : Color = Color(android.graphics.Color.parseColor("#329064"))
    when (priority)
    {
        1 -> colorPriority = Color(android.graphics.Color.parseColor("#329064"))
        2 -> colorPriority = Color(android.graphics.Color.parseColor("#C8D147"))
        3 -> colorPriority = Color(android.graphics.Color.parseColor("#D5205C"))
    }
    LazyColumn(
        modifier = Modifier.offset(y = -45.dp)
    ) {
        items(1) {
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(android.graphics.Color.parseColor("#1CABE7")),
                    contentColor = Color.White
                ),
                border = BorderStroke(0.dp, Color.Transparent),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = "Prioridad",
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.manrope_semibold)),
                        textAlign = TextAlign.Center
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(50.dp)
                                .background(
                                    color = colorPriority,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(android.graphics.Color.parseColor("#1CABE7")),
                    contentColor = Color.White
                ),
                border = BorderStroke(0.dp, Color.Transparent),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = "Porcentaje",
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.manrope_semibold))
                    )
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LinearProgressIndicator(
                            progress = percentage,
                            color = Color(android.graphics.Color.parseColor("#005A80")),
                            trackColor = Color(android.graphics.Color.parseColor("#DBECF3")),
                            modifier = Modifier
                                .width(180.dp)
                                .height(50.dp)
                                .padding(16.dp)
                        )
                    }
                }
            }
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(android.graphics.Color.parseColor("#1CABE7")),
                    contentColor = Color.White
                ),
                border = BorderStroke(0.dp, Color.Transparent),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = "Completada",
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.manrope_semibold))
                    )
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = if (complete) "Si" else "No",
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.manrope_bold))
                    )
                }
            }
        }
    }
}