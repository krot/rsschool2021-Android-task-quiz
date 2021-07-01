package com.rsschool.quiz

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import com.rsschool.quiz.databinding.FragmentQuizBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_NUM = "num"
private const val ARG_ANSWERS = "questions"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {

    private var qNum: Int = 1
    private var answers: MutableList<Int> = mutableListOf()
    private var themeId = 0
    private var binding: FragmentQuizBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            qNum = it.getInt(ARG_NUM)
            answers = it.get(ARG_ANSWERS) as MutableList<Int>
        }

        when (qNum) {
            1 -> themeId = R.style.Theme_Quiz_First
            2 -> themeId = R.style.Theme_Quiz_Second
            3 -> themeId = R.style.Theme_Quiz_Third
            4 -> themeId = R.style.Theme_Quiz_Fourth
            5 -> themeId = R.style.Theme_Quiz_Fifth
            else -> themeId = R.style.Theme_Quiz_First // default theme is first
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val value = TypedValue ()
        activity?.setTheme(themeId)
        activity?.getTheme()?.resolveAttribute(android.R.attr.statusBarColor, value, true)
        activity?.window?.statusBarColor = value.data

        binding = FragmentQuizBinding.inflate(inflater, container, false)

        binding?.previousButton?.setBackgroundColor(value.data)
        binding?.nextButton?.setBackgroundColor(value.data)

        return this.binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        if (qNum == 1) {
            binding?.toolbar?.navigationIcon = null
            binding?.previousButton?.isEnabled = false
        } else if (qNum == questions.size) {
            binding?.nextButton?.text = "Submit"
        }
        
        binding?.toolbar?.title = "Question $qNum"

        binding?.question?.text = questions[qNum-1].question
        binding?.optionOne?.text = questions[qNum-1].answers?.get(0)
        binding?.optionTwo?.text = questions[qNum-1].answers?.get(1)
        binding?.optionThree?.text = questions[qNum-1].answers?.get(2)
        binding?.optionFour?.text = questions[qNum-1].answers?.get(3)
        binding?.optionFive?.text = questions[qNum-1].answers?.get(4)

        when (answers.get(qNum-1)) {
            1 -> binding?.optionOne?.isChecked = true
            2 -> binding?.optionTwo?.isChecked = true
            3 -> binding?.optionThree?.isChecked = true
            4 -> binding?.optionFour?.isChecked = true
            5 -> binding?.optionFive?.isChecked = true
            else -> binding?.nextButton?.isEnabled = false
        }

        if (answers.get(qNum-1) != 0) {
            binding?.nextButton?.isEnabled = (true)
        } else {
            binding?.nextButton?.isEnabled = (false)
        }

        binding?.optionOne?.setOnClickListener {
            binding?.nextButton?.isEnabled = (true)
            answers.set(qNum-1, 1)
        }

        binding?.optionTwo?.setOnClickListener {
            binding?.nextButton?.isEnabled = (true)
            answers.set(qNum-1, 2)
        }
        binding?.optionThree?.setOnClickListener {
            binding?.nextButton?.isEnabled = (true)
            answers.set(qNum-1, 3)
        }
        binding?.optionFour?.setOnClickListener {
            binding?.nextButton?.isEnabled = (true)
            answers.set(qNum-1, 4)
        }
        binding?.optionFive?.setOnClickListener {
            binding?.nextButton?.isEnabled = (true)
            answers.set(qNum-1, 5)
        }

        when (qNum) {
            1 -> {
                binding?.previousButton?.isEnabled = false
                binding?.toolbar?.navigationIcon = null
            }
            in 2..4 -> binding?.nextButton?.text = "NEXT"
            5 -> binding?.nextButton?.text = "SUBMIT"
        }

        binding?.previousButton?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding?.toolbar?.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding?.nextButton?.setOnClickListener {
            if (qNum > 0 && qNum < 5) {
                (activity as MainInterface).openQuiz(qNum+1, answers)
            }
            else {
                (activity as MainInterface).openFinal(answers)
            }
        }

    }

        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment QuizFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(qNum: Int, answers: MutableList<Int>) =
            QuizFragment().apply {
                arguments = bundleOf(
                    ARG_NUM to qNum,
                    ARG_ANSWERS to answers
                )
            }
    }
}