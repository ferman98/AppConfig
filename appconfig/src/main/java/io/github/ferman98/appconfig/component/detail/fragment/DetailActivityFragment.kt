package io.github.ferman98.appconfig.component.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.ferman98.appconfig.Constants
import io.github.ferman98.appconfig.component.TreeView
import io.github.ferman98.appconfig.databinding.FragmentDetailBinding

class DetailActivityFragment : Fragment() {
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
        binding.llMain.addView(setDataIntent(), 1)
        binding.llMain.addView(setDataVariable(), 2)
    }

    private fun setDataTitle(): TreeView {
        val tv = TreeView(requireContext())
        tv.setTitle("Activity Name")
        tv.setData(mapOf("name" to Constants.DataAppConfig.activityName))
        return tv
    }

    private fun setDataIntent(): TreeView {
        val tv = TreeView(requireContext())
        tv.setTitle("Intent Extra")
        tv.setData(Constants.DataAppConfig.activityIntentExtras)
        return tv
    }

    private fun setDataVariable(): TreeView {
        val tv = TreeView(requireContext())
        tv.setTitle("Variable")
        tv.setData(Constants.DataAppConfig.activityVariable)
        return tv
    }
}