package app.aplicacion.mediaplayermiercoles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.aplicacion.mediaplayermiercoles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reproductorVideo.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ReproductorVideo::class.java
                )
            )
        }
        binding.tomarFoto.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    TomarFoto::class.java
                ))
        }

        binding.seleccionarFoto.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SeleccionarFoto::class.java
                ))
        }

        binding.codigoQr.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CodigoQr::class.java
                ))
        }

        binding.reproducirMusica.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ReproductoMusica::class.java
                ))
        }
        binding.googleMaps.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    GoogleMaps::class.java
                ))
        }
    }


}