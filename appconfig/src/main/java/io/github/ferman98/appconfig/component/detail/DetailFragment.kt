package io.github.ferman98.appconfig.component.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.github.ferman98.appconfig.R

class DetailFragment(private val position: Int) : Fragment() {
    private var _binding: VH? = null
    private val binding get() = _binding!!

    private class VH(val root: View) {
        val tvMain: TextView = root.findViewById(R.id.tvMain)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = VH(inflater.inflate(R.layout.fragment_detail, container, false))
        setLayoutListener()
        return binding.root
    }

    private fun setLayoutListener() {
        binding.tvMain.text = "Fragment $position"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}