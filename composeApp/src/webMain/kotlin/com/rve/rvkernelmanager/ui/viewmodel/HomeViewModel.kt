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
