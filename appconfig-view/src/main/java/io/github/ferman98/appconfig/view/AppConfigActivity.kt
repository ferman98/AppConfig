package io.github.ferman98.appconfig.view

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import io.github.ferman98.appconfig.view.component.TrackingButton

open class AppConfigActivity @JvmOverloads constructor(@LayoutRes contentLayoutId: Int = 0) :
    AppCompatActivity(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAllExtras()
    }

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

    private fun getAllExtras() {
        val listIntent = mutableMapOf<String, String>()
        intent.extras?.also {
            for (key in it.keySet()) {
                listIntent[key] = it.getString(key) ?: ""
            }
        }
        Log.e("TRACKING", "INTENT = " + Gson().toJson(listIntent))
    }
}