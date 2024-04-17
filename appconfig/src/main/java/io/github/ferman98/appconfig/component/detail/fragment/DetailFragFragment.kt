package io.github.ferman98.appconfig.component.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.ferman98.appconfig.Constants
import io.github.ferman98.appconfig.component.TreeView
import io.github.ferman98.appconfig.databinding.FragmentDetailBinding

class DetailFragFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        setLayoutListener()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLayoutListener() {
        binding.llMain.removeAllViews()
        binding.llMain.addView(setDataTitle(), 0)
        binding.llMain.addView(setDataVariable(), 1)
    }

    private fun setDataTitle(): TreeView {
        val tv = TreeView(requireContext())
        tv.setTitle("Fragment Name")
        tv.setData(mapOf("name" to Constants.DataAppConfig.fragmentName))
        return tv
    }

    private fun setDataVariable(): TreeView {
        val tv = TreeView(requireContext())
        tv.setTitle("Variable")
        tv.setData(Constants.DataAppConfig.fragmentVariable)
        return tv
    }
}