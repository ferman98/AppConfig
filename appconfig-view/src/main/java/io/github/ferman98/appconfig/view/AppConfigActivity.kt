package io.github.ferman98.appconfig.view

import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import io.github.ferman98.appconfig.view.component.TrackingButton

open class AppConfigActivity @JvmOverloads constructor(@LayoutRes contentLayoutId: Int = 0) :
    AppCompatActivity(contentLayoutId) {

    override fun onResume() {
        addTrackingButton()
        super.onResume()
    }

    private fun addTrackingButton() {
        val container = findViewById<FrameLayout>(android.R.id.content)
        if (container.findViewById<TrackingButton>(R.id.btnTracking) == null) {
            val fab = TrackingButton(this)
            fab.init(this)
            container.addView(fab)
        }
    }
}