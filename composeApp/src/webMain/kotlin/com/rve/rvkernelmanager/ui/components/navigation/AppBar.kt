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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.rve.rvkernelmanager.ui.components.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.composables.icons.materialsymbols.MaterialSymbols
import com.composables.icons.materialsymbols.roundedfilled.Menu

object AppBar {
    @Composable
    fun SimpleTopAppBar(onNavigate: (Any) -> Unit) {
        val uriHandler = LocalUriHandler.current

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
                        Box(contentAlignment = Alignment.Center) {
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
                                shape = MaterialTheme.shapes.extraLarge,
                                containerColor = MaterialTheme.colorScheme.surfaceBright,
                                shadowElevation = 16.dp,
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Home", color = MaterialTheme.colorScheme.onSurface) },
                                    onClick = {
                                        expanded = false
                                        onNavigate(Route.Home)
                                    },
                                )
                                DropdownMenuItem(
                                    text = { Text("Telegram", color = MaterialTheme.colorScheme.onSurface) },
                                    onClick = {
                                        expanded = false
                                        uriHandler.openUri("https://t.me/rve_enterprises")
                                    },
                                )
                            }
                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 16.dp)
                        ) {
                            TextButton(onClick = { onNavigate(Route.Home) }) {
                                Text("Home", color = MaterialTheme.colorScheme.primary)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            TextButton(onClick = { uriHandler.openUri("https://t.me/rve_enterprises") }) {
                                Text("Telegram", color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                },
            )
        }
    }
}
