package io.github.ferman98.appconfig.component.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import io.github.ferman98.appconfig.databinding.ActivityDetailBinding

class DetailView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(
    context,
    attributeSet,
    defStyleAttr,
    defStyleRes
) {

    private val binding = ActivityDetailBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun init(app: AppCompatActivity) {
        binding.vpMain.adapter = DetailPagerAdapter(app)
        binding.apply {
            TabLayoutMediator(tabs, vpMain) { tab, position ->
                tab.text = DetailPagerAdapter.tabList[position]
            }
        }
    }
}