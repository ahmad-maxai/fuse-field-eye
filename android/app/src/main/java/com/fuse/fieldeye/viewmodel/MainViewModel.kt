package com.fuse.fieldeye.viewmodel

import androidx.lifecycle.ViewModel
import com.fuse.fieldeye.domain.Evidence
import com.fuse.fieldeye.repository.FieldEyeRepository
import com.fuse.fieldeye.repository.PhaseOneOverview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


enum class PrimaryTab(val label: String) {
    HOME("Home"),
    MAP("Map"),
    ACTIVITY("Activity"),
    ANALYTICS("Analytics"),
    PROFILE("Profile"),
}

enum class ThemeChoice { SYSTEM, LIGHT, DARK }

data class MainUiState(
    val selectedTab: PrimaryTab = PrimaryTab.HOME,
    val themeChoice: ThemeChoice = ThemeChoice.SYSTEM,
    val overview: PhaseOneOverview,
)

class MainViewModel(repository: FieldEyeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState(overview = repository.overview()))
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun selectTab(tab: PrimaryTab) {
        _uiState.value = _uiState.value.copy(selectedTab = tab)
    }

    fun cycleTheme() {
        val next = when (_uiState.value.themeChoice) {
            ThemeChoice.SYSTEM -> ThemeChoice.DARK
            ThemeChoice.DARK -> ThemeChoice.LIGHT
            ThemeChoice.LIGHT -> ThemeChoice.SYSTEM
        }
        _uiState.value = _uiState.value.copy(themeChoice = next)
    }
}

/** Visible to unit tests to ensure unavailable values are never rendered as numeric zero. */
fun Evidence<*>.displayLabel(): String = when (this) {
    is Evidence.Observed<*> -> value.toString()
    is Evidence.Estimated<*> -> "Estimated · $value"
    is Evidence.Stale<*> -> "Stale · $value"
    is Evidence.Unavailable -> "Unavailable"
}
