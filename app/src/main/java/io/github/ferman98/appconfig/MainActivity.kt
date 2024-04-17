package io.github.ferman98.appconfig

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class FF(val nama: String = "", val kelas: Int = 0)
enum class Asd { NAMA, KELAS }
sealed class Asd2 {
    object Ferman : Asd2()
    object Ferman2 : Asd2()
}

class MainActivity : AppConfigActivity() {

    val ferman: String = "isi"
    val ferman2: FF = FF()
    val ferman3: Asd = Asd.KELAS
    val ferman4: Asd2 = Asd2.Ferman2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
    }

    private fun addFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment, MainFragment())
            .commit()
    }
}