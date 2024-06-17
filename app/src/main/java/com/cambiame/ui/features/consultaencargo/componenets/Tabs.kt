package com.cambiame.ui.features.consultaencargo.componenets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cambiame.R

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    tabIndex: MutableState<Int>,
) {
    val tabs = listOf("Información", "Equipo")
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = tabIndex.value,
            modifier = Modifier
                .offset(y = -35.dp)
                .padding(16.dp),
            containerColor = Color.Transparent,
            indicator = {},
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier
                        .background
                            (
                            color = Color(android.graphics.Color.parseColor("#073346")),
                            shape = if (index == 0) {
                                RoundedCornerShape(bottomStart = 10.dp, topStart = 10.dp)
                            } else {
                                RoundedCornerShape(bottomEnd = 10.dp, topEnd = 10.dp)
                            }
                        ),
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                                .background(
                                    color = if (tabIndex.value == index) Color(
                                        android.graphics.Color.parseColor(
                                            "#1CABE7"
                                        )
                                    ) else Color.Transparent,
                                    shape = RoundedCornerShape(5.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = title,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.manrope_medium))
                            )
                        }
                    },
                    selected = tabIndex.value == index,
                    onClick = { tabIndex.value = index },
                )
            }
        }
    }
}