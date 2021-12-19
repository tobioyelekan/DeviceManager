package com.example.devicemanager.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.devicemanager.data.model.Device
import com.example.devicemanager.databinding.FragmentHomeBinding
import com.example.devicemanager.ui.details.DeviceDetailsActivity
import com.example.devicemanager.util.Resource.Status
import com.example.devicemanager.util.showMsg
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var adapter: DeviceAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObserver()
    }

    private fun setupAdapter() {
        with(binding) {
            adapter = DeviceAdapter {
                openDetails(it)
            }
            deviceList.adapter = adapter
        }
    }

    private fun openDetails(device: Device) {
        val intent = Intent(requireContext(), DeviceDetailsActivity::class.java)
            .apply {
                putExtra("device", device)
            }
        startActivity(intent)
    }

    private fun setupObserver() {
        with(binding) {
            viewModel.devices.observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> {
                        progress.isVisible = true
                        empty.isVisible = false
                    }

                    Status.SUCCESS -> {
                        progress.isVisible = false
                        it.data?.let { data ->
                            adapter.submitList(data)
                            empty.isVisible = data.isEmpty()
                        }
                    }

                    Status.ERROR -> {
                        it.message?.let { msg ->
                            showMsg(msg)
                        }
                        progress.isVisible = false
                        empty.isVisible = false
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}