package com.aayar94.earthquakes.ui.fragment.LastEarthquakes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.earthquakes.databinding.RowLayoutEarthquakeBinding
import com.aayar94.earthquakes.model.EarthquakeModel

class AdapterLastEarthquakesRV(
    val onItemClick: (earthquakeModel: EarthquakeModel) -> Unit
) :
    ListAdapter<EarthquakeModel, AdapterLastEarthquakesRV.EarthquakeViewHolder>(
        BaseItemCallback()
    ) {
//<AdapterLastEarthquakesRV.EarthquakeViewHolder>() {

    //private var items: MutableList<EarthquakeModel> = mutableListOf()

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
                root.setBackgroundColor(currentList[position].magnitudeColorLight)
                root.setOnClickListener {
                    onItemClick(currentList[position])
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

    /**fun setItems(newItems: List<EarthquakeModel>) {
    val earthquakeDiffUtil = EarthquakeDiffUtil(items, newItems)
    val diffUtilResult = DiffUtil.calculateDiff(earthquakeDiffUtil)
    items = newItems as MutableList<EarthquakeModel>
    //notifyDataSetChanged()
    diffUtilResult.dispatchUpdatesTo(this)
    }*/

}

class BaseItemCallback :
    DiffUtil.ItemCallback<com.aayar94.earthquakes.model.EarthquakeModel>() {
    override fun areItemsTheSame(oldItem: EarthquakeModel, newItem: EarthquakeModel) =
        oldItem.toString() == newItem.toString()

    override fun areContentsTheSame(oldItem: EarthquakeModel, newItem: EarthquakeModel) =
        oldItem == newItem
}

