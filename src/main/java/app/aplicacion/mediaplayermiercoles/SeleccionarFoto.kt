package app.aplicacion.mediaplayermiercoles

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import app.aplicacion.mediaplayermiercoles.databinding.ActivityMain5Binding
import java.io.ByteArrayOutputStream

class SeleccionarFoto : AppCompatActivity() {
    private lateinit var binding:ActivityMain5Binding
    companion object{
        val REQUEST_CODE_SELECCIONAR_IMAGEN=101
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.seleccionarImagen.setOnClickListener { abrirGaleria() }
    }
    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_SELECCIONAR_IMAGEN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_SELECCIONAR_IMAGEN) {
                val imageUri = data?.data
                binding.imageView.setImageURI(imageUri)
            }
        }
    }
    private fun getImagenString(imageUri: Uri): String {
        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


}