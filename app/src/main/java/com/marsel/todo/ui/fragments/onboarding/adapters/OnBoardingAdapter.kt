package com.marsel.todo.ui.fragments.onboarding.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.marsel.todo.databinding.ItemOnBoardBinding
import com.marsel.todo.models.OnBoardModel

class OnBoardingAdapter(
    private val pageList: ArrayList<OnBoardModel>,
    private val onClickNext: () -> Unit,
    private val onClickGetStarted: () -> Unit
) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): OnBoardingViewHolder = OnBoardingViewHolder(
        ItemOnBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = pageList.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.onBind(pageList[position])
    }

    inner class OnBoardingViewHolder(
        private val binding: ItemOnBoardBinding
    ) : ViewHolder(binding.root) {

        fun onBind(model: OnBoardModel) {
            with(binding) {
                tvTitle.text = model.title
                tvDescription.text = model.description
                ivOnBoard.setImageResource(model.image)
                binding.btnNext.text = model.textButton

                onClickNext()

                if (binding.btnNext.text.equals("Начать")) {
                    binding.btnNext.setOnClickListener {
                        onClickGetStarted()
                    }
                } else
                    binding.btnNext.setOnClickListener {
                        onClickNext()
                    }
            }
        }
    }
}