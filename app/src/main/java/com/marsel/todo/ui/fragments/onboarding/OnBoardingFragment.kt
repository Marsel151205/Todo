package com.marsel.todo.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.marsel.todo.R
import com.marsel.todo.databinding.FragmentOnBoardingBinding
import com.marsel.todo.models.OnBoardModel
import com.marsel.todo.ui.fragments.onboarding.adapters.OnBoardingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnBoardingViewModel by viewModels()
    private val pagerList = loadDataOnBoard()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.vpOnBoarding.adapter = OnBoardingAdapter(pagerList, ::clickNext, ::clickGetStarted)
        binding.dotsIndicator.attachTo(binding.vpOnBoarding)
    }

    private fun clickNext() {
        val adapter = binding.vpOnBoarding.adapter
        val currentPosition = binding.vpOnBoarding.currentItem
        val nextPosition = currentPosition + 1
        if (nextPosition < adapter?.itemCount!!) {
            binding.vpOnBoarding.currentItem = nextPosition
        } else
            binding.vpOnBoarding.currentItem = 0
    }

    private fun clickGetStarted() {
        lifecycleScope.launch {
            viewModel.setIsFirstLogin(true)
        }
        findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
    }

    private fun loadDataOnBoard(): ArrayList<OnBoardModel> {
        val data = arrayListOf(
            OnBoardModel(
                "Управляйте своими задачами",
                "Вы можете легко и бесплатно управлять всеми своими ежедневными\nзадачами в DoMe",
                R.drawable.item_first,
                "Следующий"
            ),
            OnBoardModel(
                "Создайте распорядок дня",
                "В программе Up to do вы можете создать свой\nиндивидуальный распорядок дня, чтобы оставаться продуктивным",
                R.drawable.item_second,
                "Следующий"
            ),
            OnBoardModel(
                "Организуйте свои задачи",
                "Вы можете упорядочить свои ежедневные задачи,\nразделив их на отдельные категории",
                R.drawable.item_third,
                "Начать"
            )
        )
        return data
    }
}