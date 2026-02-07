@file:OptIn(ExperimentalMaterial3Api::class)

package com.rve.rvkernelmanager.ui.components.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

object AppBar {
    @Composable
    fun SimpleTopAppBar() {
        TopAppBar(
            title = {
                Text(
                    text = "RvKernel Manager",
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 8.dp)
                )
            },
        )
    }
}