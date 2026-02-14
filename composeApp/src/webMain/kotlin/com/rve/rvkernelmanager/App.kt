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
package com.rve.rvkernelmanager

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rve.rvkernelmanager.ui.components.navigation.AppBar.SimpleTopAppBar
import com.rve.rvkernelmanager.ui.components.navigation.Route
import com.rve.rvkernelmanager.ui.screens.DummyScreen
import com.rve.rvkernelmanager.ui.screens.HomeScreen
import com.rve.rvkernelmanager.ui.theme.RvKernelManagerTheme

@Composable
fun App() {
    val isDark = isSystemInDarkTheme()
    var isDarkTheme by remember { mutableStateOf(isDark) }

    RvKernelManagerTheme(isDarkTheme = isDarkTheme) {
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                SimpleTopAppBar(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDarkTheme = it },
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.Home,
                modifier = Modifier.padding(innerPadding),
            ) {
                composable<Route.Home> {
                    HomeScreen()
                }
                composable<Route.RvKernelLinux> {
                    DummyScreen()
                }
            }
        }
    }
}
