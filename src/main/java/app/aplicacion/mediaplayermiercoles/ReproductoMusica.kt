package app.aplicacion.mediaplayermiercoles


import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.aplicacion.mediaplayermiercoles.databinding.ActivityMain6Binding


class ReproductoMusica : AppCompatActivity() {

    private lateinit var binding: ActivityMain6Binding

    private lateinit var mediaPlayer: MediaPlayer
    private var currentSong = 0
    private val songs = arrayOf(R.raw.musica, R.raw.cancion1, R.raw.cancion2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain6Binding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, songs[currentSong])
        binding.atrasMusica.setOnClickListener { retrocederMusica() }

        binding.nextMusica.setOnClickListener { nextMusic() }
        binding.reproducirMusica.setOnClickListener { playPauseMusic() }
        mediaPlayer.setOnCompletionListener {
            nextMusic()
        }

    }

    private fun playPauseMusic() {

        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            binding.reproducirMusica.setBackgroundResource(R.drawable.ic_play)
        } else {
            mediaPlayer.start()
            binding.reproducirMusica.setBackgroundResource(R.drawable.ic_pause)
        }
    }



    private fun nextMusic() {
        currentSong = (currentSong + 1) % songs.size
        playSong()
    }


    private fun retrocederMusica() {
        currentSong = if (currentSong > 0) currentSong - 1 else songs.size - 1
        playSong()
    }

    private fun playSong() {
        mediaPlayer.reset()
        mediaPlayer = MediaPlayer.create(this, songs[currentSong])
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release() // Liberar recursos del MediaPlayer
        }
    }

}
