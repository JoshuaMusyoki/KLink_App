package com.example.songabiz.presentation.screens.app.user.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.songabiz.data.users.StoreUserData
import com.example.songabiz.presentation.components.map.rememberMapViewWithLifecycle
import com.example.songabiz.presentation.screens.auth.AuthenticationState
import com.example.songabiz.presentation.theme.GreenPrimary
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "SuspiciousIndentation"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(customPage:String){
    val snackbarHostState = remember { SnackbarHostState()}

    val drawerState =   rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val authenticateState = AuthenticationState()

//    Context
    val context = LocalContext.current
//    instantiate save email class
    val dataStore = StoreUserData(context)

    val isSignedIn = dataStore.getLogin.collectAsState(initial = "")
    val fname = dataStore.getFirstName.collectAsState(initial ="" )
    val sname = dataStore.getLastName.collectAsState(initial = "")
    val phone = dataStore.getPhone.collectAsState(initial = "")
    val email = dataStore.getEmail.collectAsState(initial = "")
    val avatar = dataStore.getAvatar.collectAsState(initial = "")
    val address = dataStore.getAddress.collectAsState(initial = "")
    val userId = dataStore.getUserId.collectAsState(initial = "")
    val sessionToken = dataStore.getSessionToken.collectAsState(initial = "")

    authenticateState.isSignedIn = isSignedIn.value!! == "true"
    authenticateState.fname = fname.value!!
    authenticateState.sname = sname.value!!
    authenticateState.phone = phone.value!!
    authenticateState.email = email.value!!
    authenticateState.avatar = avatar.value!!
    authenticateState.address = address.value!!
    authenticateState.id = userId.value!!
    authenticateState.sessionToken = sessionToken.value!!

    ModalNavigationDrawer(
          drawerState = drawerState,
          drawerContent = {
              ModalDrawerSheet(
                  drawerShape = RoundedCornerShape(15.dp),
                  drawerTonalElevation = 24.dp,
                  drawerContainerColor = GreenPrimary,
                  drawerContentColor = Color.Black,
                  content = {
                      Column(
                          modifier = Modifier
                              .fillMaxHeight()
                              .fillMaxWidth()
                              .background(Color.White)
                      ){
                      }
                  }
              )
          },
          gesturesEnabled = true,
          scrimColor = Color.White,
          content = {
              Scaffold (
//                  snackbarHost = snackbarHostState,
                  content = {
//                      .....

                      coroutineScope.launch { 
                          drawerState.open()
                      }
                  }
              )
          }
      )
}
@Composable
fun MapUI(){
    val mapView = rememberMapViewWithLifecycle()

    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ){
        val nairobi = LatLng(36.8219, 1.2921)
        val newLocation: LatLng? by remember { mutableStateOf(null) }
        val cameraPositionState = rememberCameraPositionState{
            position = CameraPosition.fromLatLngZoom(nairobi, 10f)
        }
        val context = LocalContext.current
        var location: String by remember { mutableStateOf("Your Location") }

        val requestPermisionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted: Boolean ->
                if (isGranted) {
//                Permision granted, update location
                    CoroutineScope(Dispatchers.Main).launch {
                        val newLocation = getCurrentLocation(context)
                        if (newLocation != null) {
                            val latLng = LatLng(newLocation.first, newLocation.second)
                            location =
                                "Latitude: ${newLocation.first}, Longitude: ${newLocation.second}"
                            cameraPositionState.position =
                                CameraPosition.fromLatLngZoom(latLng, 15f)
                        }
                    }
                }
            }
        )
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                if (hasLocationPermission(context)){
                    CoroutineScope(Dispatchers.Main).launch {
                        val newLocation = getCurrentLocation(context)
                        if (newLocation != null){
                            location =
                                "Latitude: ${newLocation.first}, Longitude:${newLocation.second}"
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                                LatLng(
                                    newLocation.first,
                                    newLocation.second
                                ), 15f
                            )
//                            newLocation = LatLng(newLocation.first, newLocation.second)
                        }
                        delay(1000)
                    }
                } else{
                    requestPermisionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        ){
            val liveLocation = newLocation
            if (liveLocation != null){
                Marker(
                    state = MarkerState(position = liveLocation),
                    title = "Mapper",
                    snippet = "Mapper"
                )
            }
        }
    }
    }

fun hasLocationPermission(context: Context):Boolean{
    return ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}
@SuppressLint("MissingPermission")
suspend fun getCurrentLocation(context: Context): Pair<Double, Double>? =
    suspendCoroutine { continuation ->
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location ->
//                if (location != null) {
//                    val lat = location.latitude
//                    val long = location.longitude
//                    continuation.resume(Pair(lat, long))
//                } else {
//                    continuation.resume(null)
//                }
//            }
//            .addOnFailureListener { exception ->
//                // Handle location retrieval failure
//                exception.printStackTrace()
//                continuation.resume(null)
//            }
    }

