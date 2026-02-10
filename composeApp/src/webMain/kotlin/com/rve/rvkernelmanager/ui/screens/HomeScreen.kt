@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)


package com.rve.rvkernelmanager.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.composables.icons.materialsymbols.MaterialSymbols
import com.composables.icons.materialsymbols.roundedfilled.Android
import com.composables.icons.materialsymbols.roundedfilled.Bolt
import com.composables.icons.materialsymbols.roundedfilled.Code
import com.composables.icons.materialsymbols.roundedfilled.Download
import com.composables.icons.materialsymbols.roundedfilled.Memory
import com.composables.icons.materialsymbols.roundedfilled.Palette
import com.composables.icons.materialsymbols.roundedfilled.Speed
import com.composables.icons.materialsymbols.roundedfilled.Terminal
import com.rve.rvkernelmanager.ui.viewmodel.HomeViewModel
import org.jetbrains.compose.resources.painterResource
import rvkernelmanagerwebsite.composeapp.generated.resources.Res
import rvkernelmanagerwebsite.composeapp.generated.resources.linux


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel { HomeViewModel() }
) {
    val uriHandler = LocalUriHandler.current

    val androidVersion by viewModel.androidVersion.collectAsStateWithLifecycle()
    val linuxVersion by viewModel.linuxVersion.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 32.dp)
        ) {
            item {
                HeroSection()
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "Available Platforms",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    PlatformCard(
                        title = "RvKernel Manager for Android",
                        version = androidVersion,
                        description = "Unlock the true potential of your Snapdragon device. Tune performance, battery life, and kernel parameters with a modern Material 3 interface.",
                        containerIconShape = MaterialShapes.Ghostish.toShape(),
                        icon = MaterialSymbols.RoundedFilled.Android,
                        tags = listOf("Root Required", "Android 12+", "Snapdragon", "Kernel Manager"),
                        buttonOnClick = { uriHandler.openUri("https://github.com/Rve27/RvKernel-Manager") },
                    )
                    PlatformCard(
                        title = "RvKernel Manager for Linux",
                        version = linuxVersion,
                        description = "A powerful tool to manage Linux kernels. Built with Kotlin Multiplatform for desktop environments.",
                        containerIconShape = MaterialShapes.Square.toShape(),
                        icon = painterResource(Res.drawable.linux),
                        tags = listOf("Linux Desktop", "KMP", "Kernel Manager"),
                        buttonOnClick = { uriHandler.openUri("https://github.com/Rve27/RvKernel-Manager") },
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Key Features",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        FeatureItem(
                            icon = MaterialSymbols.RoundedFilled.Speed,
                            title = "Performance Tuning",
                            description = "Adjust CPU/GPU frequencies, governors, and boost settings."
                        )
                        FeatureItem(
                            icon = MaterialSymbols.RoundedFilled.Palette,
                            title = "Material 3 Expressive",
                            description = "Beautiful UI with dynamic theming and expressive animations."
                        )
                        FeatureItem(
                            icon = MaterialSymbols.RoundedFilled.Bolt,
                            title = "Battery Monitor",
                            description = "Track voltage, temperature, and charging speeds in real-time."
                        )
                        FeatureItem(
                            icon = MaterialSymbols.RoundedFilled.Memory,
                            title = "Memory Management",
                            description = "Configure ZRAM, Swappiness, VM, and more."
                        )
                        FeatureItem(
                            icon = MaterialSymbols.RoundedFilled.Terminal,
                            title = "Open Source",
                            description = "Full transparency. Code available on GitHub."
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.surface
                    )
                )
            )
            .padding(top = 64.dp, bottom = 48.dp, start = 24.dp, end = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "RvKernel Manager",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "The ultimate tool to manage, tune, and monitor\nyour Android & Linux Kernels.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun PlatformCard(
    title: String,
    version: String?,
    description: String,
    containerIconShape: Shape,
    icon: Any?,
    tags: List<String>,
    buttonOnClick: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .clip(containerIconShape)
                        .background(MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp)
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    when (icon) {
                        is Painter -> Icon(
                            painter = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.fillMaxSize()
                        )
                        is ImageVector -> Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)

                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Card(
                        shape = MaterialTheme.shapes.extraSmall,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ),
                    ) {
                        AnimatedContent(
                            targetState = version ?: "Checking...",
                            transitionSpec = {
                                (slideInVertically { height -> height } + fadeIn())
                                    .togetherWith(slideOutVertically { height -> -height } + fadeOut())
                            },
                            label = "Version Animation"
                        ) { targetText ->
                            Text(
                                text = targetText,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }
                    }
                }
            }
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tags.forEach { tag ->
                    SuggestionChip(
                        onClick = {},
                        label = { Text(tag) },
                        enabled = false,
                        border = null,
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                            disabledLabelColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }
            }

            Box(
                modifier = Modifier.align(Alignment.End)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        shapes = ButtonDefaults.shapes(),
                        onClick = buttonOnClick,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Source code")
                            Icon(
                                imageVector = MaterialSymbols.RoundedFilled.Code,
                                contentDescription = null,
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                        }
                    }
                    Button(
                        shapes = ButtonDefaults.shapes(),
                        onClick = buttonOnClick,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Download")
                            Icon(
                                imageVector = MaterialSymbols.RoundedFilled.Download,
                                contentDescription = null,
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureItem(
    icon: ImageVector,
    title: String,
    description: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = Modifier.width(300.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
