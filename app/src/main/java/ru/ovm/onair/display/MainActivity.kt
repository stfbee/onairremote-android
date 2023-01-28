package ru.ovm.onair.display


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import ru.ovm.onair.display.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()
    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vm.data.observe(this) {
            when (it.on_air) {
                true -> vb.root.setBackgroundResource(R.color.bg_onair)
                false -> vb.root.setBackgroundResource(R.color.bg_idle)
            }
        }
    }
}
