package io.github.ferman98.appconfig.component

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.ferman98.appconfig.R
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

internal class TrackingButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FloatingActionButton(
    context,
    attributeSet,
    defStyleAttr
), View.OnTouchListener {

    companion object {
        private const val CLICK_DRAG_TOLERANCE = 10F
    }

    private var downRawX = 0F
    private var downRawY = 0F
    private var dX = 0F
    private var dY = 0F

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
        setOnTouchListener(this)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val layoutParams = view.layoutParams as MarginLayoutParams
        val action = motionEvent.action
        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                downRawX = motionEvent.rawX
                downRawY = motionEvent.rawY
                dX = view.x - downRawX
                dY = view.y - downRawY
                true
            }

            MotionEvent.ACTION_MOVE -> {
                val viewWidth = view.width
                val viewHeight = view.height
                val viewParent = view.parent as View
                val parentWidth = viewParent.width
                val parentHeight = viewParent.height

                var newX = motionEvent.rawX + dX
                newX = max(layoutParams.leftMargin * 1F, newX)
                newX = min((parentWidth - viewWidth - layoutParams.rightMargin) * 1F, newX)

                var newY = motionEvent.rawY + dY
                newY = max(layoutParams.topMargin * 1F, newY)
                newY = min((parentHeight - viewHeight - layoutParams.bottomMargin) * 1F, newY)

                view.animate()
                    .x(newX)
                    .y(newY)
                    .setDuration(0)
                    .start()
                true
            }

            MotionEvent.ACTION_UP -> {
                val upRawX = motionEvent.rawX
                val upRawY = motionEvent.rawY
                val upDX = upRawX - downRawX
                val upDY = upRawY - downRawY
                if (abs(upDX.toDouble()) < CLICK_DRAG_TOLERANCE && abs(upDY.toDouble()) < CLICK_DRAG_TOLERANCE) {
                    performClick()
                } else {
                    true
                }
            }

            else -> super.onTouchEvent(motionEvent)
        }
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
        params?.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        super.setLayoutParams(params)
    }
}