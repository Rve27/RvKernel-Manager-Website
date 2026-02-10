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
package com.rve.rvkernelmanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rve.rvkernelmanager.data.repository.GitHubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = GitHubRepository()

    private val _androidVersion = MutableStateFlow<String?>(null)
    val androidVersion: StateFlow<String?> = _androidVersion.asStateFlow()

    private val _linuxVersion = MutableStateFlow<String?>(null)
    val linuxVersion: StateFlow<String?> = _linuxVersion.asStateFlow()

    init {
        fetchVersions()
    }

    private fun fetchVersions() {
        viewModelScope.launch {
            val androidVer = repository.getLatestVersion("Rve27/RvKernel-Manager")
            _androidVersion.update { androidVer }

            val linuxVer = repository.getLatestVersion("Rve27/RvKernel-Manager-Linux")
            _linuxVersion.update { linuxVer }
        }
    }
}
