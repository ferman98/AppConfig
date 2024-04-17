package io.github.ferman98.appconfig.component.detail

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.ferman98.appconfig.R
import io.github.ferman98.appconfig.component.detail.adapter.DetailPagerAdapter
import io.github.ferman98.appconfig.extensions.dp
import io.github.ferman98.appconfig.util.DragBehavior

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
        val vpMain: ViewPager2 = root.findViewById(R.id.vp_main)
        val tabs: TabLayout = root.findViewById(R.id.tabs)
        val imgDrag: ImageView = root.findViewById(R.id.img_drag)
        val imgClose: ImageView = root.findViewById(R.id.img_close)
    }

    init {
        id = R.id.viewDetail
        addView(binding.root)
        DragBehavior(binding.imgDrag, binding.root.rootView)
    }

    fun init(app: AppCompatActivity) {
        binding.vpMain.adapter = DetailPagerAdapter(app)
        binding.apply {
            TabLayoutMediator(tabs, vpMain) { tab, position ->
                tab.text = DetailPagerAdapter.tabList[position]
            }.attach()
        }
    }

    fun setOnCloseListener(listener: OnClickListener) {
        binding.imgClose.setOnClickListener(listener)
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
        if (params is FrameLayout.LayoutParams) {
            params.gravity = Gravity.CENTER
        } else {
            x = resources.getDimension(R.dimen.x14)
            y = resources.getDimension(R.dimen.x3)
        }
        params?.width = 200.dp()
        params?.height = 300.dp()
        super.setLayoutParams(params)
    }
}