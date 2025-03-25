package eu.tutorials.quizapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import eu.tutorials.quizapp.models.QuizAppModel

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 4
        private const val DATABASE_NAME = "QuizAppDatabase"
        private const val TABLE_QUIZ_APP = "QuizAppTable"

        private const val KEY_ID = "_id"
        private const val KEY_NAME = "name"
        private const val KEY_TOTAL_QUESTIONS = "total_questions"  // New field
        private const val KEY_CORRECT_ANSWERS = "correct_answers"  // New field
        private const val KEY_DIFFICULTY = "difficulty"
        private const val KEY_RESULT = "result"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_QUIZ_APP_TABLE = """
            CREATE TABLE $TABLE_QUIZ_APP (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_NAME TEXT NOT NULL,
                $KEY_DIFFICULTY TEXT NOT NULL,
                total_questions INTEGER NOT NULL DEFAULT 0,
                correct_answers INTEGER NOT NULL DEFAULT 0,
                $KEY_RESULT INTEGER NOT NULL
            )
        """.trimIndent()
        db?.execSQL(CREATE_QUIZ_APP_TABLE)


    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3) {
            db?.execSQL("ALTER TABLE $TABLE_QUIZ_APP ADD COLUMN $KEY_TOTAL_QUESTIONS INTEGER DEFAULT 0")
            db?.execSQL("ALTER TABLE $TABLE_QUIZ_APP ADD COLUMN $KEY_CORRECT_ANSWERS INTEGER DEFAULT 0")
        }
    }


    fun addQuizApp(quizAppModel: QuizAppModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, quizAppModel.name)
        contentValues.put(KEY_DIFFICULTY, quizAppModel.difficulty)
        contentValues.put(KEY_TOTAL_QUESTIONS, quizAppModel.totalQuestions)
        contentValues.put(KEY_CORRECT_ANSWERS, quizAppModel.correctAnswers)
        contentValues.put(KEY_RESULT, quizAppModel.result)

        val result = db.insert(TABLE_QUIZ_APP, null, contentValues)

        db.close()
        return result
    }

    @SuppressLint("Range")
    fun getQuizResults(): ArrayList<QuizAppModel> {
        val quizList = ArrayList<QuizAppModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_QUIZ_APP", null) // ✅ Fix: Use TABLE_QUIZ_APP

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex(KEY_ID)
                val nameIndex = cursor.getColumnIndex(KEY_NAME)
                val difficultyIndex = cursor.getColumnIndex(KEY_DIFFICULTY)
                val totalQuestionsIndex = cursor.getColumnIndex(KEY_TOTAL_QUESTIONS)
                val correctAnswersIndex = cursor.getColumnIndex(KEY_CORRECT_ANSWERS)
                val resultIndex = cursor.getColumnIndex(KEY_RESULT)

                // Ensure all indices are valid before accessing them
                if (idIndex != -1 && nameIndex != -1 && difficultyIndex != -1 &&
                    totalQuestionsIndex != -1 && correctAnswersIndex != -1 && resultIndex != -1) {

                    val quizResult = QuizAppModel(
                        id = cursor.getInt(idIndex),
                        name = cursor.getString(nameIndex),
                        difficulty = cursor.getString(difficultyIndex),
                        totalQuestions = cursor.getInt(totalQuestionsIndex),
                        correctAnswers = cursor.getInt(correctAnswersIndex),
                        result = cursor.getInt(resultIndex)
                    )
                    quizList.add(quizResult)
                }
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return quizList
    }


    fun deleteQuizResult(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_QUIZ_APP, "$KEY_ID = ?", arrayOf(id.toString()))
        db.close()
        return result
    }

}