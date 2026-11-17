package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme =
  lightColorScheme(
    primary = CobaltPrimary,
    onPrimary = Color.White,
    primaryContainer = CobaltLight,
    onPrimaryContainer = CobaltDark,
    secondary = EmeraldAccent,
    onSecondary = Color.White,
    secondaryContainer = EmeraldLight,
    onSecondaryContainer = EmeraldDark,
    tertiary = VioletAccent,
    onTertiary = Color.White,
    tertiaryContainer = VioletLight,
    onTertiaryContainer = VioletDark,
    background = IceBackground,
    onBackground = SlateInk,
    surface = SurfaceWhite,
    onSurface = SlateInk,
    surfaceVariant = SurfaceTint,
    onSurfaceVariant = SlateMuted,
    outline = SlateBorder,
    error = RoseAccent,
    errorContainer = RoseLight,
    onErrorContainer = RoseDark,
  )

private val DarkColorScheme =
  darkColorScheme(
    primary = CobaltBright,
    onPrimary = CobaltDark,
    primaryContainer = Color(0xFF1E3A8A),
    onPrimaryContainer = CobaltLight,
    secondary = EmeraldBright,
    onSecondary = EmeraldDark,
    secondaryContainer = Color(0xFF064E3B),
    onSecondaryContainer = EmeraldLight,
    tertiary = Color(0xFFC4B5FD),
    onTertiary = VioletDark,
    tertiaryContainer = Color(0xFF4C1D95),
    onTertiaryContainer = VioletLight,
    background = NightBackground,
    onBackground = NightTextPrimary,
    surface = NightSurface,
    onSurface = NightTextPrimary,
    surfaceVariant = NightSurfaceVariant,
    onSurfaceVariant = NightTextMuted,
    outline = NightBorder,
    error = Color(0xFFFB7185),
    errorContainer = Color(0xFF881337),
    onErrorContainer = RoseLight,
  )

@Composable
fun OrganicChemTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content,
  )
}
