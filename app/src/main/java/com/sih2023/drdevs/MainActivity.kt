package com.sih2023.drdevs

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.sih2023.drdevs.ui.theme.DRDevsTheme








private val psitAA = LatLng(26.449347064508466, 80.19205235150231)
private val airforceHospital = LatLng(26.446797873756378, 80.37556576499573)
private val regencyHospital = LatLng(26.477747032569674, 80.34354328730275)
private val thanaKotwali = LatLng(26.474650246686668, 80.35161125843983)
private val fsKidwaiNagar = LatLng(26.442081636314562, 80.33221358039603)
private val home = LatLng(26.397074049510646, 80.31874031749705)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DRDevsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //Adding a map to your app
                    //ComposeMapDemo()

                    //Adding markers to your app
                    //ComposeMapDemoMarkers()

                    //Set properties on a map
                    //ComposeMapPropertiesDemo()

                    //Controlling a map's camera
                    //ComposeMapControllingCamera()

                    //Customizing a marker icon
                    // ComposeMapMapMarker()

                    //Customizing a marker's info window
                    // ComposeMapCustomizingMarker()

                    // ComposeMapMapPolyline()
                    // ComposeMapDrawTrack()
                    // no api key required //to Draw Track on Google Maps

                    //Center point
                   // ComposeMapCenterPointMapMarker()

                    LoginApplication()
                }
            }
        }
    }
}



//https://www.google.com/maps/place/London,+UK/@51.5287352,-0.3817854,10z/data=!3m1!4b1!4m5!3m4!1s0x47d8a00baf21de75:0x52963a5addd52a99!8m2!3d51.5072178!4d-0.1275862
@Composable
fun ComposeMapDemo() {
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
            title = "Singapore",
            snippet = "Marker in Singapore"
        )

    }
}



@Composable
fun ComposeMapDemoMarkers() {
    val college = LatLng(26.449347064508466, 80.19205235150231)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(college, 30f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = college),
            draggable = true,
            title = "Marker 1",
            snippet = "Marker in Singapore",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        )
    }
}






@Composable
fun ComposeMapPropertiesDemo() {
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
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
fun ComposeMapPropertiesDemo2() {
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember { mutableStateOf(MapProperties(mapType = MapType.SATELLITE)) }

    Box(Modifier.fillMaxSize()) {

        uiSettings = uiSettings.copy(zoomControlsEnabled = false)

        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings
        )
    }
}



/*
* Customizing a marker's info window
You can customize a marker's info window contents by using the MarkerInfoWindowContent element, or if you want to customize the entire info window, use the MarkerInfoWindow element instead. Both of these elements accept a content parameter to provide your customization in a composable lambda expression.*/
@Composable
fun ComposeDemoApp3() {


    val college = LatLng(26.449347064508466, 80.19205235150231)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(college, 10f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
        ){
            val icon = bitmapDescriptorFromVector(
                LocalContext.current, R.drawable.locationpin
            )
            MarkerInfoWindow(
                state = MarkerState(position = college),
                icon = icon,
            ) { marker ->
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                        )
                    ,
                ) {


                    Image(
                        painter = painterResource(id = R.drawable.locationpin),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),

                        )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))

                        //.........................Text: title
                        Text(
                            text = "Marker Title",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 60.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        //.........................Text : description
                        Text(
                            text = "Customizing a marker's info window",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))

                    }

                }

            }
        }
    }}

/*
* Customizing a marker's info window
You can customize a marker's info window contents by using the MarkerInfoWindowContent element, or if you want to customize the entire info window, use the MarkerInfoWindow element instead. Both of these elements accept a content parameter to provide your customization in a composable lambda expression.*/
@Composable
fun ComposeMapCustomizingMarker() {
    val college = LatLng(26.449347064508466, 80.19205235150231)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(college, 10f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
        ){
            val icon = bitmapDescriptorFromVector(
                LocalContext.current, R.drawable.locationpin
            )
            MarkerInfoWindow(
                state = MarkerState(position = college),
                icon = icon,
            ) { marker ->
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                        )
                    ,
                ) {


                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.map),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(80.dp)
                                .fillMaxWidth(),

                            )
                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))
                        //.........................Text: title
                        Text(
                            text = "Marker Title",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        //.........................Text : description
                        Text(
                            text = "Customizing a marker's info window",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        //.........................Spacer
                        Spacer(modifier = Modifier.height(24.dp))

                    }

                }

            }
        }
    }}

//Controlling a map's camera
@Composable
fun ComposeMapControllingCamera() {
    val college = LatLng(26.449347064508466, 80.19205235150231)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(college, 11f)
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


//..................................................................................


//private val psitAA = LatLng(26.449347064508466, 80.19205235150231)
//private val airforceHospital = LatLng(26.446797873756378, 80.37556576499573)
//private val regencyHospital = LatLng(26.477747032569674, 80.34354328730275)
//private val thanaKotwali = LatLng(26.474650246686668, 80.35161125843983)
//private val fsKidwaiNagar = LatLng(26.442081636314562, 80.33221358039603)
//private val home = LatLng(26.397074049510646, 80.31874031749705)

@Composable
fun ComposeMapMapMarker() {
    val college = LatLng(26.449347064508466, 80.19205235150231)
    val airforceHospital = LatLng(26.446797873756378, 80.37556576499573)
    val regencyHospital = LatLng(26.477747032569674, 80.34354328730275)
    val thanaKotwali = LatLng(26.474650246686668, 80.35161125843983)
    val fsKidwaiNagar = LatLng(26.442081636314562, 80.33221358039603)
    val home = LatLng(26.397074049510646, 80.31874031749705)


    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(college, 11f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState){
            MapMarker(
                position = college,
                title = "Pranveer Singh Institute of Technology",
                context = LocalContext.current,
                iconResourceId = R.drawable.locationpin
            )
            MapMarker(
                position = airforceHospital,
                title = "Airforce Hospital",
                context = LocalContext.current,
                iconResourceId = R.drawable.locationpin
            )
            MapMarker(
                position = regencyHospital,
                title = "Regency Hospital",
                context = LocalContext.current,
                iconResourceId = R.drawable.locationpin
            )
            MapMarker(
                position = thanaKotwali,
                title = "Police Station Kotwali",
                context = LocalContext.current,
                iconResourceId = R.drawable.locationpin
            )
            MapMarker(
                position = fsKidwaiNagar,
                title = "FireStation Kidwai Nagar",
                context = LocalContext.current,
                iconResourceId = R.drawable.locationpin
            )
            MapMarker(
                position = home,
                title = "Home",
                context = LocalContext.current,
                iconResourceId = R.drawable.locationpin
            )
        }
    }
}
@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    title: String,
    @DrawableRes iconResourceId: Int
) {
    val icon = bitmapDescriptorFromVector(
        context, iconResourceId
    )
    Marker(
        state = MarkerState(position = position),
        title = title,
        icon = icon,
    )
}
fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}
//.................................................................................


@Composable
fun ComposeMapCenterPointMapMarker() {
    val markerPosition = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(markerPosition, 18f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.locationpin),
                contentDescription = "marker",
            )
        }
        Text(text = "Is camera moving: ${cameraPositionState.isMoving}" +
                "\n Latitude and Longitude: ${cameraPositionState.position.target.latitude} " +
                "and ${cameraPositionState.position.target.longitude}",
            textAlign = TextAlign.Center
        )
    }
}

//.............................................................................


@Composable
fun ComposeMapDrawTrack() {
    val context = LocalContext.current
    Box(Modifier.fillMaxSize()) {
        Button(onClick = {
            //source location to the destination location
            drawTrack("Louisville", "old louisville", context)
        }) {
            Text(text = "Google map Draw Track")
        }
    }
}

private fun drawTrack(source: String, destination: String, context: Context) {
    try {
        // create a uri
        val uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/$source/$destination")
        // initializing a intent with action view.
        val i = Intent(Intent.ACTION_VIEW, uri)
        // below line is to set maps package name
        i.setPackage("com.google.android.apps.maps")
        // below line is to set flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // start activity
        context.startActivity(i)
    } catch (e: ActivityNotFoundException) {
        // when the google maps is not installed on users device
        // we will redirect our user to google play to download google maps.
        val uri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
        // initializing intent with action view.
        val i = Intent(Intent.ACTION_VIEW, uri)
        // set flags
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // to start activity
        context.startActivity(i)
    }
}



@Composable
fun ComposeMapMapPolyline() {
    val singapore = LatLng(44.811058, 20.4617586)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState){
            MapMarker(
                position = singapore,
                title = "Your Title",
                context = LocalContext.current,
                iconResourceId = R.drawable.locationpin
            )

            Polyline(
                points = listOf(
                    LatLng(44.811058, 20.4617586),
                    LatLng(44.811058, 20.4627586),
                    LatLng(44.810058, 20.4627586),
                    LatLng(44.809058, 20.4627586),
                    LatLng(44.809058, 20.4617586)
                )
                ,color = Color.Magenta
            )
        }
    }
}

@Composable
fun LoginApplication(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_page", builder = {
        composable("login_page", content = { LoginPage(navController = navController) })
        composable("register_page", content = { RegisterPage(navController = navController) })
        composable("reset_page", content = { ResetPage(navController = navController) })
    })
}

