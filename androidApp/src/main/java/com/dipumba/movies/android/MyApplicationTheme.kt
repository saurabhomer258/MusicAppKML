package com.demo.movies.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Dark4,
            primaryVariant = Dark3,
            secondary = Red,
            surface = Dark2,
            background = Dark1
        )
    } else {
        lightColors(
            primary = Color(0xFF006BEE),
            primaryVariant = Color(0xFF0048B3),
            secondary = Color(0xF303DADA)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

val Dark1 = Color(red = 17, green = 9, blue = 236, alpha = 255)
val Dark2 = Color(red = 11, green = 108, blue = 226, alpha = 255)
val Dark3 = Color(red = 15, green = 36, blue = 224, alpha = 255)
val Dark4 = Color(red = 40, green = 34, blue = 36, alpha = 255)
val Red = Color(red = 7, green = 0, blue = 220, alpha = 255)
