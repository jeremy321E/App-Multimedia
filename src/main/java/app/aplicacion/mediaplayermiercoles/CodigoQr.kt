package app.aplicacion.mediaplayermiercoles

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import app.aplicacion.mediaplayermiercoles.databinding.ActivityMain3Binding
import com.google.zxing.integration.android.IntentIntegrator

class CodigoQr : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding

    companion object {
        private val REQUEST_CODE_QR_SCAN = 102
        val REQUEST_CAMERA_PERMISSION = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lectorBarra.setOnClickListener { verificarPermisoCamara()}
    imprimirBinario()

    }

    private fun imprimirBinario(){
        val byteValue: Byte = -128
        val binaryString = byteValue.toUInt().toString(2)
        val formattedBinaryString = binaryString.padStart(8, '0')
        Log.d("binario",formattedBinaryString)
    }

    private fun escanearCodigoQR() {
        val integrator = IntentIntegrator(this)
        integrator.setPrompt("Escanea el código QR")  // Personaliza el mensaje de escaneo si lo deseas
        integrator.setBeepEnabled(true)  // Habilita/deshabilita el sonido al escanear
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                // Si se cancela el escaneo
                Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_LONG).show()
            } else {
                // Aquí procesa el resultado del escaneo del código QR y muestra el resultado en el TextView
                binding.mostrarCodigo.text = result.contents
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun verificarPermisoCamara() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            // Aquí puedes llamar a la función para escanear el código QR
            escanearCodigoQR()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado, puedes llamar a la función para escanear el código QR
                escanearCodigoQR()
            } else {
                // Permiso denegado, puedes mostrar un mensaje al usuario informando que el permiso es necesario para escanear códigos QR
            }
        }
    }
}