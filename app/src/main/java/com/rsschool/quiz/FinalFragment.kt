package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import com.rsschool.quiz.databinding.FragmentFinalBinding
import com.rsschool.quiz.databinding.FragmentQuizBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FinalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FinalFragment : Fragment() {

    private var answers: MutableList<Int> = mutableListOf()

    private var binding: FragmentFinalBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            answers = it.get("answers") as MutableList<Int>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.binding = FragmentFinalBinding.inflate(inflater, container, false)

        return this.binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var res = 0

        for (i in 0..answers.size-1) {
            if (answers[i] == questions[i].rightAnswer) res++
        }
        val max = answers.size

        binding?.result?.text = "Result: $res / $max"

        binding?.restart?.setOnClickListener{
            (activity as MainInterface).resetQuiz()
        }

        binding?.closeApp?.setOnClickListener{
            ActivityCompat.finishAffinity(requireActivity())
        }

        binding?.share?.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_SUBJECT,"Quiz Result")
            val res1 = questions[0].answers?.get(answers[0]-1)
            val q1 = questions[0].question
            val txt1 = "1. $q1 - $res1"
            val res2 = questions[1].answers?.get(answers[1]-1)
            val q2 = questions[1].question
            val txt2 = "2. $q2 - $res2"
            val res3 = questions[2].answers?.get(answers[2]-1)
            val q3 = questions[2].question
            val txt3 = "3. $q3 - $res3"
            val res4 = questions[3].answers?.get(answers[3]-1)
            val q4 = questions[3].question
            val txt4 = "4. $q4 - $res4"
            val res5 = questions[4].answers?.get(answers[4]-1)
            val q5 = questions[4].question
            val txt5 = "5. $q5 - $res5"
            intent.putExtra(Intent.EXTRA_TEXT, "Result: $res / $max \n $txt1 \n $txt2 \n $txt3 \n $txt4 \n $txt5 ")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share"))
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param answers
         * @return A new instance of fragment FinalFragment.
         */
        @JvmStatic
        fun newInstance(answers: MutableList<Int>): FinalFragment =
            FinalFragment().apply {
                arguments = bundleOf(
                    "answers" to answers
                )
            }
    }
}