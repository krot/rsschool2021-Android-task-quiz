package com.rsschool.quiz

data class Question(val question: String,
                    val answers: Array<String>?,
                    val rightAnswer: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (question != other.question) return false
        if (answers != null) {
            if (other.answers == null) return false
            if (!answers.contentEquals(other.answers)) return false
        } else if (other.answers != null) return false
        if (rightAnswer != other.rightAnswer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = question.hashCode()
        result = 31 * result + (answers?.contentHashCode() ?: 0)
        result = 31 * result + rightAnswer
        return result
    }
}

val questions = listOf(
    Question("Question One",
        arrayOf("Answer One", "Answer Two", "Answer Three", "Answer Four", "Answer Five"), 1),
    Question("Question Two",
        arrayOf("Answer One", "Answer Two", "Answer Three", "Answer Four", "Answer Five"), 2),
    Question("Question Three",
        arrayOf("Answer One", "Answer Two", "Answer Three", "Answer Four", "Answer Five"), 3),
    Question("Question Four",
        arrayOf("Answer One", "Answer Two", "Answer Three", "Answer Four", "Answer Five"), 4),
    Question("Question Five",
        arrayOf("Answer One", "Answer Two", "Answer Three", "Answer Four", "Answer Five"), 5),
)