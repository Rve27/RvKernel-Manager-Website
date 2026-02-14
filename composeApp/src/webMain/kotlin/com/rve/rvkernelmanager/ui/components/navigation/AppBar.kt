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
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.rve.rvkernelmanager.ui.components.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.composables.icons.materialsymbols.MaterialSymbols
import com.composables.icons.materialsymbols.roundedfilled.Dark_mode
import com.composables.icons.materialsymbols.roundedfilled.Light_mode
import com.composables.icons.materialsymbols.roundedfilled.Menu

object AppBar {
    @Composable
    fun SimpleTopAppBar(
        isDarkTheme: Boolean,
        onThemeChange: (Boolean) -> Unit,
        onNavigate: (Any) -> Unit
    ) {
        val uriHandler = LocalUriHandler.current
        val interactionSource = remember { MutableInteractionSource() }

        var expanded by remember { mutableStateOf(false) }

        BoxWithConstraints {
            val isCompact = maxWidth < 600.dp

            TopAppBar(
                title = {
                    Text(
                        text = "RvKernel Manager",
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                },
                actions = {
                    if (isCompact) {
                        IconButton(
                            onClick = { expanded = !expanded },
                            modifier = Modifier.padding(end = 8.dp),
                        ) {
                            Image(
                                imageVector = MaterialSymbols.RoundedFilled.Menu,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                contentDescription = "Menu",
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            shape = MaterialTheme.shapes.large,
                            containerColor = MaterialTheme.colorScheme.surfaceBright,
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                    Text(
                                        text = "Home",
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                        }
                                },
                                onClick = {
                                    expanded = false
                                    onNavigate(Route.Home)
                                },
                            )
                            DropdownMenuItem(
                                text = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(
                                            text = "RvKernel Linux",
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                },
                                onClick = {
                                    expanded = false
                                    onNavigate(Route.RvKernelLinux)
                                },
                            )
                            DropdownMenuItem(
                                text = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(
                                            text = "Telegram",
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                },
                                onClick = {
                                    expanded = false
                                    uriHandler.openUri("https://t.me/rve_enterprises")
                                },
                            )
                            HorizontalDivider()
                            DropdownMenuItem(
                                interactionSource = interactionSource,
                                text = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        val slowSpatialSpec = MaterialTheme.motionScheme.slowSpatialSpec<Float>()
                                        val slowEffectsSpec = MaterialTheme.motionScheme.slowSpatialSpec<Float>()

                                        Switch(
                                            interactionSource = interactionSource,
                                            thumbContent = {
                                                AnimatedContent(
                                                    targetState = isDarkTheme,
                                                    transitionSpec = {
                                                        (scaleIn(
                                                            animationSpec = slowSpatialSpec
                                                        ) + fadeIn(
                                                            animationSpec = slowEffectsSpec
                                                        )).togetherWith(scaleOut(
                                                            animationSpec = slowSpatialSpec
                                                        ) + fadeOut(
                                                            animationSpec = slowEffectsSpec
                                                        ))
                                                    },
                                                    label = "Switch thumb icon animation"
                                                ) { isDark ->
                                                    Icon(
                                                        imageVector = if (isDark)
                                                            MaterialSymbols.RoundedFilled.Dark_mode
                                                        else
                                                            MaterialSymbols.RoundedFilled.Light_mode,
                                                        contentDescription = if (isDark) "Dark mode" else "Light mode",
                                                        modifier = Modifier.size(SwitchDefaults.IconSize)
                                                    )
                                                }
                                            },
                                            checked = isDarkTheme,
                                            onCheckedChange = { onThemeChange(it) }
                                        )
                                    }
                                },
                                onClick = {
                                    onThemeChange(!isDarkTheme)
                                }
                            )
                        }
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 16.dp)
                        ) {
                            TextButton(onClick = { onNavigate(Route.Home) }) {
                                Text("Home", color = MaterialTheme.colorScheme.primary)
                            }
                            TextButton(onClick = { onNavigate(Route.RvKernelLinux) }) {
                                Text("RvKernel Linux", color = MaterialTheme.colorScheme.primary)
                            }
                            TextButton(onClick = { uriHandler.openUri("https://t.me/rve_enterprises") }) {
                                Text("Telegram", color = MaterialTheme.colorScheme.primary)
                            }
                            IconButton(onClick = { onThemeChange(!isDarkTheme) }) {
                                val slowSpatialSpec = MaterialTheme.motionScheme.slowSpatialSpec<IntOffset>()
                                val slowEffectsSpec = MaterialTheme.motionScheme.slowEffectsSpec<Float>()

                                AnimatedContent(
                                    targetState = isDarkTheme,
                                    transitionSpec = {
                                        if (targetState) {
                                            (slideInVertically(slowSpatialSpec) { height -> -height } + fadeIn(slowEffectsSpec)) togetherWith
                                                    (slideOutVertically(slowSpatialSpec) { height -> height } + fadeOut(slowEffectsSpec))
                                        } else {
                                            (slideInVertically(slowSpatialSpec) { height -> height } + fadeIn(slowEffectsSpec)) togetherWith
                                                    (slideOutVertically(slowSpatialSpec) { height -> -height } + fadeOut(slowEffectsSpec))
                                        }.using(
                                            SizeTransform(clip = false),
                                        )
                                    },
                                ) { isDark ->
                                    Image(
                                        imageVector = if (isDark) MaterialSymbols.RoundedFilled.Light_mode else MaterialSymbols.RoundedFilled.Dark_mode,
                                        contentDescription = if (isDark) "Dark mode" else "Light mode",
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                    )
                                }
                            }
                        }
                    }
                },
            )
        }
    }
}
