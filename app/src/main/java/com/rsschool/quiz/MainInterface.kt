package com.rsschool.quiz

interface MainInterface {
    fun openQuiz(qNumber: Int, answers: MutableList<Int>)
    fun openFinal(answers: MutableList<Int>)
    fun resetQuiz()
}