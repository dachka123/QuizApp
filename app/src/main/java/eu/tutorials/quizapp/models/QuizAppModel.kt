package eu.tutorials.quizapp.models


data class QuizAppModel(
    val id: Int,
    val name: String,
    val difficulty: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val result: Int
)