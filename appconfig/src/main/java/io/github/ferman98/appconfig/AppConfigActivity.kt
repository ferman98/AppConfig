package io.github.ferman98.appconfig

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.github.ferman98.appconfig.component.TrackingButton
import io.github.ferman98.appconfig.component.detail.DetailView
import kotlin.reflect.full.declaredMemberProperties

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
                val frag = getActiveFragment()
                val activityProp = this.getVariable()
                val fragmentProp = frag?.getVariable() ?: mapOf()
                Constants.DataAppConfig.activityName = componentName.className
                Constants.DataAppConfig.activityVariable = activityProp
                Constants.DataAppConfig.fragmentName = frag?.javaClass?.name ?: "null"
                Constants.DataAppConfig.fragmentVariable = fragmentProp

                fab.visibility = View.INVISIBLE
                container.addDetailView()?.also {
                    it.setOnCloseListener { _ ->
                        container.removeView(it)
                        fab.visibility = View.VISIBLE
                    }
                }
            }
            container.addView(fab)
        }
    }

    private fun FrameLayout.addDetailView(): DetailView? {
        if (this.findViewById<TrackingButton>(R.id.viewDetail) == null) {
            val d = DetailView(this@AppConfigActivity)
            d.init(this@AppConfigActivity)
            this.addView(d)
            return d
        }
        return null
    }

    private fun getAllExtras() {
        val listIntent = mutableMapOf<String, String>()
        intent.extras?.also {
            for (key in it.keySet()) {
                listIntent[key] = it.getString(key) ?: ""
            }
        }
        Constants.DataAppConfig.activityIntentExtras = listIntent
    }

    private fun <T : Any> T.getVariable(): Map<String, String> {
        val data = mutableMapOf<String, String>()
        this::class.declaredMemberProperties
            .forEach { p ->
                data[p.name] = this::class.java.getDeclaredField(p.name).run {
                    isAccessible = true
                    get(this@getVariable)?.toString() ?: "null"
                }
            }
        return data
    }

    private fun getActiveFragment(): Fragment? {
        return this
            .supportFragmentManager
            .fragments
            .find { it.isVisible }
    }
}