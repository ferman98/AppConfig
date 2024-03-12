package io.github.ferman98.appconfig

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import io.github.ferman98.appconfig.component.TrackingButton
import io.github.ferman98.appconfig.component.detail.DetailView

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
            fab.setOnClickListener {
                var listVariable = ""
                componentName.className.also { className ->
                    Log.e("TRACKING", className + " - " + getActiveFragment())
                }
                this::class.members.forEach {
                    listVariable += it.name + ", "
                }
                Log.e("TRACKING", listVariable)
                container.addView(detailPage())
            }
            container.addView(fab)
        }
    }

    private fun detailPage(): DetailView {
        val d = DetailView(this)
        d.init(this)
        return d
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

    private fun getActiveFragment(): String {
        var fragmentName = "Null"
        this
            .supportFragmentManager
            .fragments
            .forEach {
                if (it.isVisible) {
                    fragmentName = it.javaClass.name ?: "Null"
                }
            }
        return fragmentName
    }
}