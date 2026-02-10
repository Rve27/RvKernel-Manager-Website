/*
 * Copyright (c) 2026 Rve <rve27github@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

// Dear programmer:
// When I wrote this code, only god and
// I knew how it worked.
// Now, only god knows it!
//
// Therefore, if you are trying to optimize
// this routine and it fails (most surely),
// please increase this counter as a
// warning for the next person:
//
// total hours wasted here = 254
//
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.composables.icons.materialsymbols.MaterialSymbols
import com.composables.icons.materialsymbols.roundedfilled.Android
import com.composables.icons.materialsymbols.roundedfilled.Code
import com.composables.icons.materialsymbols.roundedfilled.Download
import com.rve.rvkernelmanager.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import rvkernelmanagerwebsite.composeapp.generated.resources.Res
import rvkernelmanagerwebsite.composeapp.generated.resources.linux

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel { HomeViewModel() }) {
    val uriHandler = LocalUriHandler.current

    val androidVersion by viewModel.androidVersion.collectAsStateWithLifecycle()
    val linuxVersion by viewModel.linuxVersion.collectAsStateWithLifecycle()

    val androidUrl by viewModel.androidDownloadUrl.collectAsStateWithLifecycle()
    val debUrl by viewModel.linuxDebUrl.collectAsStateWithLifecycle()
    val rpmUrl by viewModel.linuxRpmUrl.collectAsStateWithLifecycle()

    var showLinuxDialog by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()

    val isHeroVisible by remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.any { it.index == 0 }
        }
    }

    if (showLinuxDialog) {
        LinuxDownloadDialog(
            onDismissRequest = { showLinuxDialog = false },
            debUrl = debUrl,
            rpmUrl = rpmUrl,
            onOpenLink = { url ->
                uriHandler.openUri(url)
                showLinuxDialog = false
            },
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 32.dp),
        ) {
            item {
                HeroSection(isAnimText = isHeroVisible)
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    Text(
                        text = "Available Platforms",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    PlatformCard(
                        title = "RvKernel Manager for Android",
                        version = androidVersion,
                        description = "Unlock the true potential of your Snapdragon device. " +
                            "Tune performance, battery life, and kernel parameters with a modern Material 3 interface.",
                        containerIconShape = MaterialShapes.Ghostish.toShape(),
                        icon = MaterialSymbols.RoundedFilled.Android,
                        tags = listOf("Root Required", "Android 12+", "Snapdragon", "Kernel Manager"),
                        onSourceClick = { uriHandler.openUri("https://github.com/Rve27/RvKernel-Manager") },
                        onDownloadClick = { androidUrl?.let { uriHandler.openUri(it) } },
                        isDownloadEnabled = androidUrl != null,
                    )
                    PlatformCard(
                        title = "RvKernel Manager for Linux",
                        version = linuxVersion,
                        description = "A powerful tool to manage Linux kernels. Built with Kotlin Multiplatform for desktop environments.",
                        containerIconShape = MaterialShapes.Square.toShape(),
                        icon = painterResource(Res.drawable.linux),
                        tags = listOf("Linux Desktop", "KMP", "Kernel Manager"),
                        onSourceClick = { uriHandler.openUri("https://github.com/Rve27/RvKernel-Manager-Linux") },
                        onDownloadClick = { showLinuxDialog = true },
                        isDownloadEnabled = (debUrl != null || rpmUrl != null),
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroSection(isAnimText: Boolean) {
    val actions = listOf("manage", "tune", "monitor")
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(isAnimText) {
        if (isAnimText) {
            while (true) {
                delay(2000)
                currentIndex = (currentIndex + 1) % actions.size
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.surface,
                    ),
                ),
            )
            .padding(top = 64.dp, bottom = 48.dp, start = 24.dp, end = 24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "RvKernel Manager",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "The ultimate tool to ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    AnimatedContent(
                        targetState = actions[currentIndex],
                        transitionSpec = {
                            (slideInVertically { height -> height } + fadeIn())
                                .togetherWith(slideOutVertically { height -> -height } + fadeOut())
                        },
                        label = "Action Animation",
                    ) { targetAction ->
                        Text(
                            text = targetAction,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
                Text(
                    text = "your Android & Linux Kernels.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun PlatformCard(
    title: String,
    version: String?,
    description: String,
    containerIconShape: Shape,
    icon: Any?,
    tags: List<String>,
    onSourceClick: () -> Unit,
    onDownloadClick: () -> Unit,
    isDownloadEnabled: Boolean = true,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
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
                    contentAlignment = Alignment.Center,
                ) {
                    when (icon) {
                        is Painter -> Icon(
                            painter = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.fillMaxSize(),
                        )

                        is ImageVector -> Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),

                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Card(
                        shape = MaterialTheme.shapes.extraSmall,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                    ) {
                        AnimatedContent(
                            targetState = version ?: "Checking...",
                            transitionSpec = {
                                (slideInVertically { height -> height } + fadeIn())
                                    .togetherWith(slideOutVertically { height -> -height } + fadeOut())
                            },
                            label = "Version Animation",
                        ) { targetText ->
                            Text(
                                text = targetText,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onTertiary,
                                modifier = Modifier.padding(horizontal = 4.dp),
                            )
                        }
                    }
                }
            }
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                tags.forEach { tag ->
                    SuggestionChip(
                        onClick = {},
                        label = { Text(tag) },
                        enabled = false,
                        border = null,
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
                            disabledLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        ),
                    )
                }
            }

            Box(
                modifier = Modifier.align(Alignment.End),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Button(
                        shapes = ButtonDefaults.shapes(),
                        onClick = onSourceClick,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text("Source code")
                            Icon(
                                imageVector = MaterialSymbols.RoundedFilled.Code,
                                contentDescription = null,
                                modifier = Modifier.size(ButtonDefaults.IconSize),
                            )
                        }
                    }
                    Button(
                        shapes = ButtonDefaults.shapes(),
                        onClick = onDownloadClick,
                        enabled = isDownloadEnabled,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text("Download")
                            Icon(
                                imageVector = MaterialSymbols.RoundedFilled.Download,
                                contentDescription = null,
                                modifier = Modifier.size(ButtonDefaults.IconSize),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LinuxDownloadDialog(onDismissRequest: () -> Unit, debUrl: String?, rpmUrl: String?, onOpenLink: (String) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = { Icon(painterResource(Res.drawable.linux), contentDescription = null, modifier = Modifier.size(24.dp)) },
        title = {
            Text(text = "Select your Linux Distro", textAlign = TextAlign.Center)
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                OutlinedCard(
                    onClick = { debUrl?.let { onOpenLink(it) } },
                    enabled = debUrl != null,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Icon(MaterialSymbols.RoundedFilled.Download, contentDescription = null)
                        Column {
                            Text("Debian / Ubuntu", style = MaterialTheme.typography.titleSmall)
                            Text("Download .deb package", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }

                OutlinedCard(
                    onClick = { rpmUrl?.let { onOpenLink(it) } },
                    enabled = rpmUrl != null,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Icon(MaterialSymbols.RoundedFilled.Download, contentDescription = null)
                        Column {
                            Text("Fedora / Red Hat", style = MaterialTheme.typography.titleSmall)
                            Text("Download .rpm package", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Arch Linux / Manjaro",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Install via AUR using your favorite helper:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        SelectionContainer {
                            Text(
                                "yay -S rvkernel-manager",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f), shape = MaterialTheme.shapes.small)
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                shapes = ButtonDefaults.shapes(),
                onClick = onDismissRequest,
            ) {
                Text("Close")
            }
        },
    )
}
