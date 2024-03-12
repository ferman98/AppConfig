package io.github.ferman98.appconfig.component.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.ferman98.appconfig.R
import io.github.ferman98.appconfig.extensions.dp

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

    private val binding =
        VH(LayoutInflater.from(context).inflate(R.layout.activity_detail, this, false))

    private class VH(val root: View) {
        val vpMain: ViewPager2 = root.findViewById(R.id.vpMain)
        val tabs: TabLayout = root.findViewById(R.id.tabs)
    }

    init {
        id = R.id.viewDetail
        addView(binding.root)
    }

    fun init(app: AppCompatActivity) {
        binding.vpMain.adapter = DetailPagerAdapter(app)
        binding.apply {
            TabLayoutMediator(tabs, vpMain) { tab, position ->
                tab.text = DetailPagerAdapter.tabList[position]
            }.attach()
        }
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
        params?.width = 150.dp()
        params?.height = 300.dp()
        super.setLayoutParams(params)
    }
}