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
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "QuizAppDatabase"
        private const val TABLE_QUIZ_APP = "QuizAppTable"

        private const val KEY_ID = "_id"
        private const val KEY_NAME = "name"
        private const val KEY_DIFFICULTY = "difficulty"
        private const val KEY_RESULT = "result"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_QUIZ_APP_TABLE = """
            CREATE TABLE $TABLE_QUIZ_APP (
                $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_NAME TEXT NOT NULL,
                $KEY_DIFFICULTY TEXT NOT NULL,
                $KEY_RESULT INTEGER NOT NULL
            )
        """.trimIndent()

        db?.execSQL(CREATE_QUIZ_APP_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_QUIZ_APP")
        onCreate(db)
    }

    fun addQuizApp(quizAppModel: QuizAppModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, quizAppModel.name)
        contentValues.put(KEY_DIFFICULTY, quizAppModel.difficulty)
        contentValues.put(KEY_RESULT, quizAppModel.result)

        val result = db.insert(TABLE_QUIZ_APP, null, contentValues)

        db.close()
        return result
    }

    @SuppressLint("Range")
    fun getQuizResults(): ArrayList<QuizAppModel> {
        val quizList = ArrayList<QuizAppModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_QUIZ_APP", null)

        if (cursor.moveToFirst()) {
            do {
                val quiz = QuizAppModel(
                    id = cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                    name = cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    difficulty = cursor.getString(cursor.getColumnIndex(KEY_DIFFICULTY)),
                    result = cursor.getInt(cursor.getColumnIndex(KEY_RESULT))
                )
                quizList.add(quiz)
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