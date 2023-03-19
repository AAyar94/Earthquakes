package com.aayar94.earthquakes.ui.fragment.LastEarthquakes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.earthquakes.R
import com.aayar94.earthquakes.databinding.RowLayoutEarthquakeBinding
import com.aayar94.earthquakes.model.EarthquakeModel

class AdapterLastEarthquakesRV(
) :
    ListAdapter<EarthquakeModel, AdapterLastEarthquakesRV.EarthquakeViewHolder>(
        BaseItemCallback()
    ) {

    inner class EarthquakeViewHolder(private val binding: RowLayoutEarthquakeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            with(binding) {
                txtMag.text = currentList[position].magnitudeText
                txtName.text = currentList[position].name
                txtDate.text = currentList[position].date
                txtTime.text = currentList[position].time
                divider.setBackgroundColor(currentList[position].magnitudeColor)
                cardMag.setCardBackgroundColor(currentList[position].magnitudeColor)
                rootCardView.setCardBackgroundColor(currentList[position].magnitudeColor)
                root.animation = AnimationUtils.loadAnimation(root.context, R.anim.rv_item_anim)
                root.transitionName = currentList[position].name
                root.setOnClickListener {
                    val extras = FragmentNavigatorExtras(
                        binding.root to "CardViewTransition"
                    )
                    val action =
                        LastEarthquakesFragmentDirections.actionLastEarthquakesFragmentToMapsFragment(
                            currentList[position]
                        )
                    androidx.navigation.Navigation.findNavController(it).navigate(action, extras)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        val binding =
            RowLayoutEarthquakeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return EarthquakeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        holder.bindData(position)
    }
}


class BaseItemCallback :
    DiffUtil.ItemCallback<EarthquakeModel>() {
    override fun areItemsTheSame(oldItem: EarthquakeModel, newItem: EarthquakeModel) =
        oldItem.toString() == newItem.toString()

    override fun areContentsTheSame(oldItem: EarthquakeModel, newItem: EarthquakeModel) =
        oldItem == newItem
}

