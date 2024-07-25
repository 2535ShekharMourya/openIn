package com.example.openinappassignment.view

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.openinappassignment.R
import com.example.openinappassignment.adapter.LinkAdapter
import com.example.openinappassignment.databinding.FragmentLinkBinding
import com.example.openinappassignment.model.Link
import com.example.openinappassignment.repositary.ApiClient
import com.example.openinappassignment.repositary.DashboardRepository
import com.example.openinappassignment.viewmodel.DashboardViewModel
import com.example.openinappassignment.viewmodel.DashboardViewModelFactory

class LinkFragment : Fragment(), LinkAdapter.ItemClicked {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: FragmentLinkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLinkBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService = ApiClient.create()
        val repository = DashboardRepository(apiService)
        val factory = DashboardViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]


        val adapter = LinkAdapter(this)
        binding.rvLinks.adapter = adapter
        val topLinks: ArrayList<Link> = ArrayList()
        val recentLinks: ArrayList<Link> = ArrayList()
        viewModel.dashboardData.observe(viewLifecycleOwner) {
            topLinks.clear()
            recentLinks.clear()
            topLinks.addAll(it.data.topLinks)
            recentLinks.addAll(it.data.recentLinks)
            adapter.submitList(topLinks)
        }

        val token =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
        viewModel.fetchDashboardData(token)


        binding.topLinks.setOnClickListener {
            binding.recentLinks.setTextColor(Color.parseColor("#999CA0"))
            binding.topLinks.setTextColor(Color.WHITE)
            binding.recentLinks.setBackgroundResource(0)
            binding.topLinks.setBackgroundResource(R.drawable.button_border)
            adapter.submitList(topLinks)
        }
        binding.recentLinks.setOnClickListener {
            binding.topLinks.setTextColor(Color.parseColor("#999CA0"))
            binding.recentLinks.setTextColor(Color.WHITE)
            binding.topLinks.setBackgroundResource(0)
            binding.recentLinks.setBackgroundResource(R.drawable.button_border)
            adapter.submitList(recentLinks)
        }

    }

    override fun onItemClicked(item: Link) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(item.webLink))
    }

    override fun onLinkClicked(item: Link) {
        copyToClipboard(requireContext(), item.webLink)
    }

    private fun copyToClipboard(context: Context, text: String) {
        // Get the ClipboardManager using the correct type
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        // Create a ClipData object with the text to be copied
        val clip = ClipData.newPlainText("web link", text)

        // Set the clip data to the clipboard
        clipboard.setPrimaryClip(clip)

        // Show a toast message to indicate that the text has been copied
        Toast.makeText(context, "Link copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}