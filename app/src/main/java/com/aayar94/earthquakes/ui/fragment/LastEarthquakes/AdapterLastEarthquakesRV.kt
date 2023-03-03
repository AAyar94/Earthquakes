package com.aayar94.earthquakes.ui.fragment.LastEarthquakes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.earthquakes.databinding.RowLayoutEarthquakeBinding
import com.aayar94.earthquakes.model.EarthquakeModel

class AdapterLastEarthquakesRV :
    RecyclerView.Adapter<AdapterLastEarthquakesRV.EarthquakeViewHolder>() {

    private val items: MutableList<EarthquakeModel> = mutableListOf()

    inner class EarthquakeViewHolder(private val binding: RowLayoutEarthquakeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            with(binding) {
                txtMag.text = items[position].magnitudeText
                txtName.text = items[position].name
                txtDate.text = items[position].date
                txtTime.text = items[position].time
                divider.setBackgroundColor(items[position].magnitudeColor)
                cardMag.setBackgroundColor(items[position].magnitudeColor)
                root.setBackgroundColor(items[position].magnitudeColorLight)
                root.setOnClickListener {
                    val action =
                        LastEarthquakesFragmentDirections.actionLastEarthquakesFragmentToEarthquakeDetailsFragment(
                            items[position]
                        )
                    Navigation.findNavController(it).navigate(action)
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
        return items.size
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        holder.bindData(position)
    }

    fun setItems(items: List<EarthquakeModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

