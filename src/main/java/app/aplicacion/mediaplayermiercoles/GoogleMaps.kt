package app.aplicacion.mediaplayermiercoles

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.Manifest
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMaps : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
companion object{
    const val REQUES_CODE_LOCATION=0
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)
        createFragment()
    }

    private fun createFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { this }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMrker()
        enabledLocation()

    }

    private fun createMrker() {
        val coordenas = LatLng(-0.197237, -78.488903)
        val marker = MarkerOptions().position(coordenas).title("mi ubicacion")
        map.addMarker(marker)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordenas, 18f), 4000, null)
    }

    private fun isLocationPermission() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun enabledLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermission()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocalPermision()
        }
    }

    private fun requestLocalPermision() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
    Toast.makeText(this,"activa los permisos",Toast.LENGTH_LONG).show()
        }else{
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUES_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUES_CODE_LOCATION->if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = true
            }else{
                Toast.makeText(this,"para activar los permisos ve a ajustes y actialos",Toast.LENGTH_LONG).show()
            }
            else ->{}

        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if (!isLocationPermission()){
            map.isMyLocationEnabled = false
            Toast.makeText(this,"para activar los permisos ve a ajustes y actialos",Toast.LENGTH_LONG).show()
        }
    }


}
