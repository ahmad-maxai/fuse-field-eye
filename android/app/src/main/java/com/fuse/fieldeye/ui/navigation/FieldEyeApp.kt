package com.fuse.fieldeye.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fuse.fieldeye.repository.PhaseOneEmptyRepository
import com.fuse.fieldeye.ui.screens.PrimaryScreen
import com.fuse.fieldeye.ui.theme.FieldEyeTheme
import com.fuse.fieldeye.viewmodel.MainViewModel
import com.fuse.fieldeye.viewmodel.PrimaryTab

@Composable
fun FieldEyeApp() {
    val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory())
    val state by viewModel.uiState.collectAsState()

    FieldEyeTheme(choice = state.themeChoice) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                NavigationBar {
                    PrimaryTab.entries.forEach { tab ->
                        NavigationBarItem(
                            selected = state.selectedTab == tab,
                            onClick = { viewModel.selectTab(tab) },
                            icon = { Icon(imageFor(tab), contentDescription = null) },
                            label = { androidx.compose.material3.Text(tab.label) },
                        )
                    }
                }
            },
        ) { innerPadding ->
            PrimaryScreen(
                tab = state.selectedTab,
                overview = state.overview,
                themeChoice = state.themeChoice,
                onThemeChange = viewModel::cycleTheme,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Composable
private fun imageFor(tab: PrimaryTab) = when (tab) {
    PrimaryTab.HOME -> Icons.Outlined.Home
    PrimaryTab.MAP -> Icons.Outlined.Place
    PrimaryTab.ACTIVITY -> Icons.AutoMirrored.Outlined.List
    PrimaryTab.ANALYTICS -> Icons.Outlined.DateRange
    PrimaryTab.PROFILE -> Icons.Outlined.Person
}

private class MainViewModelFactory : androidx.lifecycle.ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(MainViewModel::class.java)) {
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
        return MainViewModel(PhaseOneEmptyRepository()) as T
    }
}
