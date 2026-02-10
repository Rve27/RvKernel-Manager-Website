@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.rve.rvkernelmanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.materialkolor.rememberDynamicColorScheme
import org.jetbrains.compose.resources.Font
import rvkernelmanagerwebsite.composeapp.generated.resources.Res
import rvkernelmanagerwebsite.composeapp.generated.resources.google_sans_flex

@Composable
fun RvKernelManagerTheme(
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val colorScheme = rememberDynamicColorScheme(seedColor = Color(0xFFEBAC00), isDark = isDark)

    val googleSansFontFamily = FontFamily(
        Font(Res.font.google_sans_flex, weight = FontWeight.Medium),
        Font(Res.font.google_sans_flex, weight = FontWeight.Normal),
        Font(Res.font.google_sans_flex, weight = FontWeight.Bold),
        Font(Res.font.google_sans_flex, weight = FontWeight.SemiBold)
    )

    val defaultTypography = Typography()
    val appTypography = Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = googleSansFontFamily),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = googleSansFontFamily),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = googleSansFontFamily),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = googleSansFontFamily),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = googleSansFontFamily),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = googleSansFontFamily),
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = googleSansFontFamily),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = googleSansFontFamily),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = googleSansFontFamily),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = googleSansFontFamily),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = googleSansFontFamily),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = googleSansFontFamily),
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = googleSansFontFamily),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = googleSansFontFamily),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = googleSansFontFamily)
    )

    MaterialExpressiveTheme(
        colorScheme = colorScheme,
        motionScheme = MotionScheme.expressive(),
        typography = appTypography,
        content = content
    )
}
