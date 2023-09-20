package com.sih2023.drdevs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.sih2023.drdevs.ui.theme.DRDevsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DRDevsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize() ,
                    color = MaterialTheme.colorScheme.background
                ) {

                    ComposeMapControllingCamera()
                }
            }
        }
    }
}



//      26.449347064508466, 80.19205235150231   (PSIT Cordinates AA block)
//      26.397074049510646, 80.31874031749705   (Home Cordinates)



@Composable
fun Greeting(name: String , modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DRDevsTheme {
        Greeting("Android")

    }
}
@Composable
fun LocationMark() {
    val college = LatLng(26.449347064508466, 80.19205235150231)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(college, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = college),
            title = "PSIT College",
            snippet = "Marker in AA block"
        )
    }
}


@Composable
fun MapMarkersMovable() {
    val singapore = LatLng(26.449347064508466, 80.19205235150231)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom (singapore, 10f)
    }
    GoogleMap(
         Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker (
            state = rememberMarkerState (position = singapore),
            draggable = true,
            title="Marker 1",
            snippet = "Marker in Singapore",
            icon = BitmapDescriptorFactory.defaultMarker (BitmapDescriptorFactory.HUE_RED))
    }
}

//HUE_REDHUE_RED@Composable
//fun ComposeMapPropertiesDemo2() {
//    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
//    val properties by remember {
//        mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
//        Box(Modifier.fillMaxSize()) {
//            this: BoxScope
//            uiSettings = uiSettings.copy(zoomControlsEnabled = false)
//            GoogleMap(
//                modifier = Modifier.matchParentSize() ,
//                properties = properties ,
//                UiSettings = uiSettings
//            ))
//        }
//
//    }
//}

@Composable
fun ComposeMapPropertiesDemo() {
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings
        )
        Switch(
            checked = uiSettings.zoomControlsEnabled,
            onCheckedChange = {
                uiSettings = uiSettings.copy(zoomControlsEnabled = it)
            }
        )
    }
}

@Composable
fun ComposeMapControllingCamera() {
    val singapore = LatLng(26.449347064508466, 80.19205235150231)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState){
        }
        Button(onClick = {
            // Move the camera to a new zoom level
            cameraPositionState.move(CameraUpdateFactory.zoomIn())
        }) {
            Text(text = "Zoom In")
        }
    }
}