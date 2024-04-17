package io.github.ferman98.appconfig.component

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.ferman98.appconfig.R
import io.github.ferman98.appconfig.util.DragBehavior

internal class TrackingButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FloatingActionButton(
    context,
    attributeSet,
    defStyleAttr
) {

    init {
        id = R.id.btnTracking
        setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_phonesetting_24))
        elevation = 100F
        setPaddingRelative(
            resources.getDimensionPixelOffset(R.dimen.x6),
            resources.getDimensionPixelOffset(R.dimen.x3),
            resources.getDimensionPixelOffset(R.dimen.x6),
            resources.getDimensionPixelOffset(R.dimen.x3),
        )
        setBackgroundColor(Color.BLUE)
        DragBehavior(this)
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
        params?.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        super.setLayoutParams(params)
    }
}