package com.fuse.fieldeye.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fuse.fieldeye.repository.PhaseOneOverview
import com.fuse.fieldeye.viewmodel.PrimaryTab
import com.fuse.fieldeye.viewmodel.ThemeChoice
import com.fuse.fieldeye.viewmodel.displayLabel

@Composable
fun PrimaryScreen(
    tab: PrimaryTab,
    overview: PhaseOneOverview,
    themeChoice: ThemeChoice,
    onThemeChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = tab.label,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "FIELD EYE · Phase 1 foundation",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
        )
        Spacer(Modifier.height(20.dp))

        when (tab) {
            PrimaryTab.HOME -> HomeReviewContent(overview)
            PrimaryTab.MAP -> ReviewNotice(
                title = "Map not connected",
                body = "No location, basemap, place search, route, or provider integration is included in this review build.",
            )
            PrimaryTab.ACTIVITY -> ReviewNotice(
                title = "No personal activity stored",
                body = "Sessions and visits are not collected in Phase 1. Local database fixtures are synthetic test data only.",
            )
            PrimaryTab.ANALYTICS -> ReviewNotice(
                title = "Analytics unavailable",
                body = "No raw field evidence is collected, so no totals or derived analytics are shown.",
            )
            PrimaryTab.PROFILE -> ProfileReviewContent(themeChoice, onThemeChange)
        }
        Spacer(Modifier.weight(1f))
        Text(
            text = "PRE-DEVELOPMENT FOUNDATION · NO LIVE SERVICES",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun HomeReviewContent(overview: PhaseOneOverview) {
    ReviewNotice(
        title = "Native shell ready for review",
        body = "Five-tab navigation and the light/dark theme foundation are in place. No live data or field operations are enabled.",
    )
    Spacer(Modifier.height(16.dp))
    Text("Sessions · ${overview.sessions.displayLabel()}", style = MaterialTheme.typography.bodyLarge)
    Spacer(Modifier.height(8.dp))
    Text("Visits · ${overview.visits.displayLabel()}", style = MaterialTheme.typography.bodyLarge)
    Spacer(Modifier.height(8.dp))
    Text("Recorded distance · ${overview.recordedDistanceMeters.displayLabel()}", style = MaterialTheme.typography.bodyLarge)
}

@Composable
private fun ProfileReviewContent(themeChoice: ThemeChoice, onThemeChange: () -> Unit) {
    ReviewNotice(
        title = "Display foundation",
        body = "Theme selection is local to this running review shell; it is not persisted as a user setting.",
    )
    Spacer(Modifier.height(16.dp))
    Text("Current theme preference: ${themeChoice.name.lowercase().replaceFirstChar { it.uppercase() }}")
    Spacer(Modifier.height(8.dp))
    androidx.compose.material3.OutlinedButton(onClick = onThemeChange) {
        Text("Cycle system / dark / light")
    }
    Spacer(Modifier.height(20.dp))
    Text("Account, privacy, location, sync, and profile controls are intentionally not implemented.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant)
}

@Composable
private fun ReviewNotice(title: String, body: String) {
    androidx.compose.material3.Card {
        Column(Modifier.padding(18.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(8.dp))
            Text(body, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
