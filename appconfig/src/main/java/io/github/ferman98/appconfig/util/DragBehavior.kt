package io.github.ferman98.appconfig.util

import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class DragBehavior(
    touchArea: View,
    private val target: View? = null
) : View.OnTouchListener {

    companion object {
        private const val CLICK_DRAG_TOLERANCE = 10F
    }

    private var downRawX = 0F
    private var downRawY = 0F
    private var dX = 0F
    private var dY = 0F

    init {
        touchArea.setOnTouchListener(this)
    }

    override fun onTouch(v: View, motionEvent: MotionEvent): Boolean {
        val view = target ?: v
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
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
                    view.performClick()
                } else {
                    true
                }
            }

            else -> view.onTouchEvent(motionEvent)
        }
    }
}