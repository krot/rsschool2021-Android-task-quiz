package com.rsschool.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainInterface {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resetQuiz()
    }

    private fun prepareAnswers(): MutableList<Int> {
        val answers: MutableList<Int> = mutableListOf<Int>()
        for (n in questions.indices)
            answers.add(0)

        return answers
    }

    private fun openQuizFragment(qNumber: Int, answers: MutableList<Int>) {

        val quizFragment: Fragment = QuizFragment.newInstance(qNumber, answers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack("$qNumber").replace(R.id.fragmentContainerView, quizFragment).commit()
    }

    override fun resetQuiz() {
        val answers = prepareAnswers()
        openQuiz(1, answers)
    }

    override fun openQuiz(qNumber: Int, answers: MutableList<Int>) {
        openQuizFragment(qNumber, answers)
    }

    override fun openFinal(answers: MutableList<Int>) {

        val finalFragment: Fragment = FinalFragment.newInstance(answers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack("final").replace(R.id.fragmentContainerView, finalFragment).commit()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1){
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)}
        super.onBackPressed()
    }


}