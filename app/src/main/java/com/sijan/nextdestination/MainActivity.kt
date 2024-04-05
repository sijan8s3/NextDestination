package com.sijan.nextdestination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.sijan.nextdestination.data.Destination
import com.sijan.nextdestination.ui.DestinationDetails
import com.sijan.nextdestination.ui.DestinationList
import com.sijan.nextdestination.ui.theme.NextDestinationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = MainViewModelFactory(applicationContext)
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setContent {
            NextDestinationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val destinations = viewModel.destinations
                    val selectedDestination = remember { mutableStateOf<Destination?>(null) }

                    val skipPartiallyExpanded by remember { mutableStateOf(false) }
                    val edgeToEdgeEnabled by remember { mutableStateOf(false) }
                    val bottomSheetState = rememberModalBottomSheetState(
                        skipPartiallyExpanded = skipPartiallyExpanded
                    )

                    if (isScreenWiderThan600dp()) {
                        // Tablet View: Show list and detail screens side by side
                        Row(modifier = Modifier.fillMaxSize()) {
                            // List
                            DestinationList(
                                destinations = destinations,
                                modifier = Modifier.weight(0.3f)
                            ) { destination ->
                                selectedDestination.value = destination
                            }

                            // Detail
                            Column(
                                modifier = Modifier.weight(0.7f)
                            ) {
                                selectedDestination.value?.let { destination ->
                                    DestinationDetails(
                                        destination = destination,
                                        modifier = Modifier.fillMaxHeight(),
                                        attributes = viewModel.attributes
                                    ) {

                                    }
                                }
                            }
                        }

                    } else {
                        // Mobile View: Show list and detail screens one after the other
                        Column(modifier = Modifier.fillMaxSize()) {
                            // List
                            DestinationList(destinations = destinations) { destination ->
                                selectedDestination.value = destination
                            }
                             //Detail
                            selectedDestination.value?.let {destination ->
                                val windowInsets = if (edgeToEdgeEnabled)
                                    WindowInsets(0) else BottomSheetDefaults.windowInsets
                                ModalBottomSheet(
                                    onDismissRequest = {
                                        selectedDestination.value = null
                                    },
                                    sheetState = bottomSheetState,
                                    windowInsets = windowInsets
                                ) {
                                    DestinationDetails(destination = destination, attributes = viewModel.attributes) {
                                        selectedDestination.value = null
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




@Composable
fun isScreenWiderThan600dp(): Boolean {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    return screenWidthDp > 600.dp
}

