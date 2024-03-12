package io.github.ferman98.appconfig.component.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.ferman98.appconfig.databinding.FragmentDetailBinding

class DetailFragment(private val position: Int) : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
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