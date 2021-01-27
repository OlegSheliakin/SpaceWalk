package com.spacexwalk.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.spacexwalk.databinding.ItemListLaunchBinding
import com.spacexwalk.domain.entities.Launch
import java.time.ZoneId

/**
 * Created by olegsheliakin on 27.01.2021.
 */
class MainAdapter() : ListAdapter<Launch, MainAdapter.LaunchViewHolder>(LaunchDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val binding = ItemListLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LaunchViewHolder(private val binding: ItemListLaunchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(launch: Launch) {
            with(binding) {
                textViewDateTime.text =
                    launch.launchDateUtc.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toString()
                textViewDays.text =
                    launch.staticFireDateUtc?.withZoneSameInstant(ZoneId.systemDefault())?.toString()
                textViewMissionName.text = launch.missionName
                textViewRocket.text = launch.rocketId
            }
        }
    }

    private object LaunchDiffCallback : DiffUtil.ItemCallback<Launch>() {

        override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean =
            oldItem.missionName == newItem.missionName

        override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean =
            oldItem == newItem
    }
}
