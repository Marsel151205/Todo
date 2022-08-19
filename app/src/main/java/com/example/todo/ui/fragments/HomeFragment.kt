package com.example.todo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.databinding.FragmentHomeBinding
import com.example.todo.ui.adapters.TaskAdapter
import com.example.todo.ui.fragments.models.CreateDataModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    var model: CreateDataModel? = null
    val list = arrayListOf<CreateDataModel>()
    var adapter = TaskAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
        if (arguments != null) {
            model = arguments?.getSerializable("model") as CreateDataModel
            list.add(model!!)
        }
        adapter = TaskAdapter(list)
        binding.recyclerTask.adapter = adapter
    }

    private fun initClicker() {
        binding.btnAddTask.setOnClickListener {
            val dialog = CreateTaskData()
            dialog.show(requireActivity().supportFragmentManager, "")
        }
    }

    override fun onStop() {
        super.onStop()
        list.clear()
    }
}