package com.aayar94.earthquakes.utils

import androidx.recyclerview.widget.DiffUtil
import com.aayar94.earthquakes.model.EarthquakeModel

class EarthquakeDiffUtil(
    private val oldList: MutableList<EarthquakeModel>,
    private val newList: List<EarthquakeModel>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
