package io.github.ferman98.appconfig.component

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginTop
import io.github.ferman98.appconfig.R

class KeyValueView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(
    context,
    attributeSet,
    defStyleAttr
) {

    init {
        if (isInEditMode) {
            setText("key", "value")
        }
    }

    fun setText(key: String, value: String) {
        val txt = "$key: $value"
        val end = key.length
        val spannable = SpannableString(txt)
        val foregroundSpan = ForegroundColorSpan(Color.RED)
        spannable.setSpan(foregroundSpan, 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = spannable
    }
}