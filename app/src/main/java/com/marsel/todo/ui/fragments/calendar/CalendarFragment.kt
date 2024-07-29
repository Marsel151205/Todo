package com.marsel.todo.ui.fragments.calendar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marsel.todo.databinding.FragmentCalendarBinding
import com.marsel.todo.models.DayWithCalendarModel
import com.marsel.todo.ui.fragments.calendar.adapters.DayAdapter
import java.text.SimpleDateFormat
import java.util.*

//@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private var currentCalendar = Calendar.getInstance()
    private lateinit var adapter: DayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListeners()
    }

    private fun setupView() {
        setupCalendar()
    }

    private fun setupCalendar() {
        val days = generateDays()
        val todayPosition = findTodayPosition(days)
        adapter = DayAdapter(days, { day -> onDaySelected(day) }, todayPosition)
        binding.calendarDays.rvCalendar.adapter = adapter
        if (todayPosition != RecyclerView.NO_POSITION) {
            onDaySelected(days[todayPosition])
            binding.calendarDays.rvCalendar.scrollToPosition(todayPosition)
        }
    }

    private fun setupListeners() {
        binding.calendarDays.btnBack.setOnClickListener {
            shiftDays(-8)
        }

        binding.calendarDays.btnNext.setOnClickListener {
            shiftDays(8)
        }
    }

    private fun shiftDays(daysToShift: Int) {
        currentCalendar.add(Calendar.DAY_OF_YEAR, daysToShift)
        val days = generateDays()
        adapter.updateDays(days)
        binding.calendarDays.rvCalendar.scrollToPosition(0)
    }

    private fun generateDays(): List<DayWithCalendarModel> {
        val days = mutableListOf<DayWithCalendarModel>()
        val dayOfWeekFormat = SimpleDateFormat("EEE", Locale.getDefault())
        val dayOfMonthFormat = SimpleDateFormat("d", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())

        for (i in 0..7) {
            val calendar = (currentCalendar.clone() as Calendar).apply {
                add(Calendar.DAY_OF_YEAR, i)
            }
            val dayOfWeek = dayOfWeekFormat.format(calendar.time)
            val dayOfMonth = dayOfMonthFormat.format(calendar.time).toInt()
            val month = monthFormat.format(calendar.time)
            val year = yearFormat.format(calendar.time)
            days.add(DayWithCalendarModel(dayOfWeek, dayOfMonth, month, year))
        }
        return days
    }

    private fun findTodayPosition(days: List<DayWithCalendarModel>): Int {
        val calendar = Calendar.getInstance()
        val todayDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val todayMonth = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
        val todayYear = calendar.get(Calendar.YEAR)

        return days.indexOfFirst {
            it.dayOfMonth == todayDayOfMonth &&
                    it.month == todayMonth
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onDaySelected(day: DayWithCalendarModel) {
        binding.calendarDays.tvSelectedDate.text = "${day.month}\n${day.year}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}