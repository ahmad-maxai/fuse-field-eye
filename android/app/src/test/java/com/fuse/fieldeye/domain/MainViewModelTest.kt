package com.fuse.fieldeye.domain

import com.fuse.fieldeye.repository.PhaseOneEmptyRepository
import com.fuse.fieldeye.viewmodel.MainViewModel
import com.fuse.fieldeye.viewmodel.PrimaryTab
import com.fuse.fieldeye.viewmodel.ThemeChoice
import org.junit.Assert.assertEquals
import org.junit.Test

class MainViewModelTest {
    @Test
    fun everyApprovedPrimaryTabCanBeSelected() {
        val viewModel = MainViewModel(PhaseOneEmptyRepository())
        PrimaryTab.entries.forEach { tab ->
            viewModel.selectTab(tab)
            assertEquals(tab, viewModel.uiState.value.selectedTab)
        }
        assertEquals(5, PrimaryTab.entries.size)
    }

    @Test
    fun themePreferenceCyclesLocally() {
        val viewModel = MainViewModel(PhaseOneEmptyRepository())
        assertEquals(ThemeChoice.SYSTEM, viewModel.uiState.value.themeChoice)
        viewModel.cycleTheme()
        assertEquals(ThemeChoice.DARK, viewModel.uiState.value.themeChoice)
        viewModel.cycleTheme()
        assertEquals(ThemeChoice.LIGHT, viewModel.uiState.value.themeChoice)
        viewModel.cycleTheme()
        assertEquals(ThemeChoice.SYSTEM, viewModel.uiState.value.themeChoice)
    }
}
