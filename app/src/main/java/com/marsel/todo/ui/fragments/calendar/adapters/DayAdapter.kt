package com.marsel.todo.ui.fragments.calendar.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marsel.todo.databinding.ItemDayCalendarScreenBinding
import com.marsel.todo.models.DayWithCalendarModel

class DayAdapter(
    private var days: List<DayWithCalendarModel>,
    private val onDaySelected: (DayWithCalendarModel) -> Unit,
    private val initialSelectedPosition: Int
) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    private var selectedPosition = initialSelectedPosition

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder =
        DayViewHolder(
            ItemDayCalendarScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: DayViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.onBind(days[position], position == selectedPosition)
        holder.itemView.setOnClickListener {
            if (selectedPosition != position) {
                notifyItemChanged(selectedPosition)
                selectedPosition = position
                notifyItemChanged(selectedPosition)
                onDaySelected(days[position])
            }
        }
    }

    override fun getItemCount(): Int = days.size

    fun updateDays(newDays: List<DayWithCalendarModel>) {
        days = newDays
        selectedPosition = RecyclerView.NO_POSITION
        notifyDataSetChanged()
    }

    class DayViewHolder(private val binding: ItemDayCalendarScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: DayWithCalendarModel, isSelected: Boolean) {
            binding.dayOfWeek.text = model.dayOfWeek
            binding.dayOfMonth.text = model.dayOfMonth.toString()
            binding.root.isSelected = isSelected
        }
    }
}