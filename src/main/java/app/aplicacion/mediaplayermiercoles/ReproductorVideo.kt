package app.aplicacion.mediaplayermiercoles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.aplicacion.mediaplayermiercoles.databinding.ActivityMain4Binding


class ReproductorVideo : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_SELECCIONAR_VIDEO = 100

    }

    private lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.seleccionarVideo.setOnClickListener { buscarVideo() }
        binding.pause.setOnClickListener { pauseVideo() }
        binding.reproducir.setOnClickListener { reproducir() }

    }

    private fun buscarVideo(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "video/*"

        // Iniciar la galería
        startActivityForResult(intent, REQUEST_CODE_SELECCIONAR_VIDEO)

    }
    private fun reproducir(){
        binding.videoView.start()
    }
    private fun pauseVideo(){
        binding.videoView.pause()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Si el resultado es positivo
        if (resultCode == Activity.RESULT_OK) {
            // Obtener la ruta del video seleccionado
            val rutaVideo = data?.data

            // Asignar la ruta del video al VideoView
            binding.videoView.setVideoURI(rutaVideo)

            // Iniciar la reproducción del video
            binding.videoView.start()
        }
    }
}