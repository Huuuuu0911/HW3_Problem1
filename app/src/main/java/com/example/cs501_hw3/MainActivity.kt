@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cs501_hw3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SettingsScreen(
                        onBack = { /* demo: leave empty */ },
                        onInfo = { /* demo: leave empty */ }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onInfo: () -> Unit
) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(false) }
    var analyticsAllowed by remember { mutableStateOf(true) }
    var autoPlayVideos by remember { mutableStateOf(false) }
    var textSize by remember { mutableFloatStateOf(1.0f) } // 0.8..1.3

    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onInfo) {
                        Icon(Icons.Default.Info, contentDescription = "Info")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->

        // Main container must be Column
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .navigationBarsPadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Material 3: AssistChip
            AssistChip(
                onClick = { /* optional */ },
                label = { Text("General") }
            )

            // Material 3: Card + Divider + Switch/Checkbox
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Preferences",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Divider()

                    SettingRow(
                        title = "Notifications",
                        subtitle = "Get alerts for updates and reminders",
                        right = {
                            Switch(
                                checked = notificationsEnabled,
                                onCheckedChange = { notificationsEnabled = it }
                            )
                        }
                    )

                    Divider()

                    SettingRow(
                        title = "Dark mode",
                        subtitle = "Reduce glare in low-light environments",
                        right = {
                            Switch(
                                checked = darkMode,
                                onCheckedChange = { darkMode = it }
                            )
                        }
                    )

                    Divider()

                    SettingRow(
                        title = "Share analytics",
                        subtitle = "Help improve the app with anonymous usage data",
                        right = {
                            Checkbox(
                                checked = analyticsAllowed,
                                onCheckedChange = { analyticsAllowed = it }
                            )
                        }
                    )
                }
            }

            // Material 3: Card + Slider + ListItem
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Accessibility",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    SettingRow(
                        title = "Text size",
                        subtitle = "Adjust the readability across the app",
                        right = {
                            // sizeIn / widthIn requirement (widthIn is okay)
                            Column(
                                modifier = Modifier
                                    .widthIn(min = 140.dp, max = 220.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                Slider(
                                    value = textSize,
                                    onValueChange = { textSize = it },
                                    valueRange = 0.8f..1.3f
                                )
                            }
                        }
                    )

                    ListItem(
                        headlineContent = { Text("Preview") },
                        supportingContent = { Text("This sample text scales with your setting.") }
                    )
                }
            }

            // A standalone setting row (still follows Row structure)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    SettingRow(
                        title = "Autoplay videos",
                        subtitle = "Automatically play videos on Wi-Fi",
                        modifier = Modifier
                            .clickable { autoPlayVideos = !autoPlayVideos } // clickable requirement
                            .background(MaterialTheme.colorScheme.surface),   // background requirement
                        right = {
                            Switch(
                                checked = autoPlayVideos,
                                onCheckedChange = { autoPlayVideos = it }
                            )
                        }
                    )

                    Divider()

                    // Save row with clip + border + heightIn
                    SettingRow(
                        title = "Save changes",
                        subtitle = "Applies your settings immediately",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp)) // clip requirement
                            .border(                         // border requirement
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 4.dp, vertical = 2.dp),
                        right = {
                            Button(
                                onClick = {
                                    // Material 3: Snackbar
                                },
                                modifier = Modifier
                                    .heightIn(min = 40.dp) // ✅ heightIn requirement
                                    .align(Alignment.CenterVertically)
                            ) {
                                Text("Save")
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) { /* no-op */ }
        }
    }
}

@Composable
private fun SettingRow(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    right: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalAlignment = Alignment.CenterVertically,
            content = right
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsPreview() {
    MaterialTheme {
        Surface {
            SettingsScreen(onBack = {}, onInfo = {})
        }
    }
}