package io.github.ferman98.appconfig.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import io.github.ferman98.appconfig.databinding.ViewTreeBinding

class TreeView @JvmOverloads constructor(
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
    private val binding = ViewTreeBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        if (isInEditMode) {
            setTitle("Title")
            setData(mapOf("Key" to "Value"))
        }
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setData(data: Map<String, String>) {
        binding.llChild.removeAllViews()
        if (data.isEmpty()) {
            val kv = KeyValueView(context)
            kv.text = "null"
            binding.llChild.addView(kv)
        } else {
            data.onEachIndexed { i, (k, v) ->
                val kv = KeyValueView(context)
                kv.setText(k, v)
                binding.llChild.addView(kv, i)
            }
        }
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
        params?.height = LayoutParams.WRAP_CONTENT
        params?.width = LayoutParams.MATCH_PARENT
        super.setLayoutParams(params)
    }
}