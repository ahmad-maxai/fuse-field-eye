package com.fuse.fieldeye.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fuse.fieldeye.viewmodel.ThemeChoice

private val Evergreen = Color(0xFF183A33)
private val EvergreenDark = Color(0xFFB7D8C9)
private val WarmPaper = Color(0xFFF4F2EA)
private val DarkCanvas = Color(0xFF101A18)
private val SurfaceLight = Color(0xFFFFFEFA)
private val SurfaceDark = Color(0xFF192622)
private val TextLight = Color(0xFF18231F)
private val TextDark = Color(0xFFF0F2EA)
private val TealLight = Color(0xFF08766F)
private val TealDark = Color(0xFF63C4B5)
private val AmberLight = Color(0xFF986000)
private val AmberDark = Color(0xFFE9B85C)

private val LightColors = lightColorScheme(
    primary = Evergreen,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD8E8DF),
    onPrimaryContainer = Evergreen,
    secondary = TealLight,
    onSecondary = Color.White,
    tertiary = AmberLight,
    background = WarmPaper,
    onBackground = TextLight,
    surface = SurfaceLight,
    onSurface = TextLight,
    surfaceVariant = Color(0xFFE8EAE3),
    onSurfaceVariant = Color(0xFF52615A),
    error = Color(0xFFA33F35),
)

private val DarkColors = darkColorScheme(
    primary = EvergreenDark,
    onPrimary = Color(0xFF183A33),
    primaryContainer = Color(0xFF25483E),
    onPrimaryContainer = Color(0xFFD8E8DF),
    secondary = TealDark,
    onSecondary = Color(0xFF003731),
    tertiary = AmberDark,
    background = DarkCanvas,
    onBackground = TextDark,
    surface = SurfaceDark,
    onSurface = TextDark,
    surfaceVariant = Color(0xFF21312C),
    onSurfaceVariant = Color(0xFFB5C1BA),
    error = Color(0xFFF08C7E),
)

@Composable
fun FieldEyeTheme(
    choice: ThemeChoice,
    content: @Composable () -> Unit,
) {
    val useDark = when (choice) {
        ThemeChoice.SYSTEM -> isSystemInDarkTheme()
        ThemeChoice.LIGHT -> false
        ThemeChoice.DARK -> true
    }
    MaterialTheme(
        colorScheme = if (useDark) DarkColors else LightColors,
        content = content,
    )
}
