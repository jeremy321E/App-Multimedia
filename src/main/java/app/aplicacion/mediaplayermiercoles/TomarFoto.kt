package app.aplicacion.mediaplayermiercoles

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.Manifest
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import app.aplicacion.mediaplayermiercoles.databinding.ActivityMain2Binding

class TomarFoto : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    companion object{
        private val REQUEST_IMAGE_CAPTURE = 123
        private val REQUEST_CAMERA_PERMISSION = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tomarFoto2.setOnClickListener { verificarPermisoCamara() }

    }


    private fun verificarPermisoCamara() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
        } else {
            // Aquí puedes llamar a la función para abrir la cámara y tomar la fotografía
            dispatchTakePictureIntent()
        }
    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imageView2.setImageBitmap(imageBitmap)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado, puedes llamar a la función para abrir la cámara y tomar la fotografía
                dispatchTakePictureIntent()
            } else {
                // Permiso denegado, puedes mostrar un mensaje al usuario informando que el permiso es necesario para tomar fotografías
            }
        }
    }


}