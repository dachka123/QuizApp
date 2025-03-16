package eu.tutorials.quizapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.models.Question

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"
    const val DIFFICULTY_LEVEL = "difficulty_level"


    fun getEasyQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,"what country this flag belong to?",
            R.drawable.ic_flag_of_argentina,"Argentina","Australia",
            "Armenia","Austria",1
        )
        questionsList.add(que1)

        val que2 = Question(
            2,"what country this flag belong to?",
            R.drawable.ic_flag_of_australia,"Argentina","Australia",
            "Armenia","Algeria",2
        )
        questionsList.add(que2)

        val que3 = Question(
            3,"what country this flag belong to?",
            R.drawable.ic_flag_of_belgium,"Belarus","Belgium",
            "Bulgaria","Greece",2
        )
        questionsList.add(que3)

        val que4 = Question(
            4,"what country this flag belong to?",
            R.drawable.ic_flag_of_brazil,"Brazil","Dominic Rep",
            "Belarus","Argentina",1
        )
        questionsList.add(que4)

        val que5 = Question(
            5,"what country this flag belong to?",
            R.drawable.ic_flag_of_denmark,"Congo","Finland",
            "Denmark","Sweden",3
        )
        questionsList.add(que5)

        val que6 = Question(
            6,"what country this flag belong to?",
            R.drawable.ic_flag_of_france,"France","Italy",
            "Czechia","Belgium",1
        )
        questionsList.add(que6)

        val que7 = Question(
            7,"what country this flag belong to?",
            R.drawable.ic_flag_of_mexico,"Brazil","Canada",
            "Portugal","Mexico",4
        )
        questionsList.add(que7)

        val que8 = Question(
            8,"what country this flag belong to?",
            R.drawable.ic_flag_of_china,"Mongolia","Korean rep",
            "China","Indonesia",3
        )
        questionsList.add(que8)

        val que9 = Question(
            9,"what country this flag belong to?",
            R.drawable.ic_flag_of_japan,"Hong kong","Japan",
            "China","North Korea",2
        )
        questionsList.add(que9)

        val que10 = Question(
            10,"what country this flag belong to?",
            R.drawable.ic_flag_of_turkey,"Azerbaijan","Greece",
            "Turkey","Algeria",3
        )
        questionsList.add(que10)

        return questionsList
    }

    fun getNormalQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,"what country this flag belong to?",
            R.drawable.ic_flag_of_croatia,"Croatia","Netherlands",
            "Thailand","Morocco",1
        )
        questionsList.add(que1)

        val que2 = Question(
            2,"what country this flag belong to?",
            R.drawable.ic_flag_of_slovakia,"Slovakia","Slovenia",
            "Lithuania","Hungary",1
        )
        questionsList.add(que2)

        val que3 = Question(
            3,"what country this flag belong to?",
            R.drawable.ic_flag_of_vietnam,"China","Vietnam",
            "Singapore","Chile",2
        )
        questionsList.add(que3)

        val que4 = Question(
            4,"what country this flag belong to?",
            R.drawable.ic_flag_of_luxembourg,"Netherlands","Hungary",
            "Armenia","Luxembourg",4
        )
        questionsList.add(que4)

        val que5 = Question(
            5,"what country this flag belong to?",
            R.drawable.ic_flag_of_nigeria,"Turkmenistan","Madagascar",
            "Nigeria","Mali",3
        )
        questionsList.add(que5)

        val que6 = Question(
            6,"what country this flag belong to?",
            R.drawable.ic_flag_of_north_macedonia,"Iceland","Scotland",
            "Poland","North Macedonia",4
        )
        questionsList.add(que6)

        val que7 = Question(
            7,"what country this flag belong to?",
            R.drawable.ic_flag_of_venezuela,"Venezuela","Ecuador",
            "Colombia","Cameroon",1
        )
        questionsList.add(que7)

        val que8 = Question(
            8,"what country this flag belong to?",
            R.drawable.ic_flag_of_paraguay,"Bolivia","Paraguay",
            "Costa Rica","Uruguay",2
        )
        questionsList.add(que8)

        val que9 = Question(
            9,"what country this flag belong to?",
            R.drawable.ic_flag_of_wales,"Ireland","Ivery Coast",
            "wales","Denmark",3
        )
        questionsList.add(que9)

        val que10 = Question(
            10,"what country this flag belong to?",
            R.drawable.ic_flag_of_mongolia,"Lebanon","Mongolia",
            "Nepal","Philippines",2
        )
        questionsList.add(que10)

        return questionsList
    }

    fun getHardQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,"what country this flag belong to?",
            R.drawable.ic_flag_of_moldova,"Moldova","Latvia",
            "Malta","Andorra",1
        )
        questionsList.add(que1)

        val que2 = Question(
            2,"what country this flag belong to?",
            R.drawable.ic_flag_of_san_marino,"Lichtenstein","San Marino",
            "Sri Lanka","Algeria",2
        )
        questionsList.add(que2)

        val que3 = Question(
            3,"what country this flag belong to?",
            R.drawable.ic_flag_of_honduras,"Netherlands","Nicaragua",
            "Honduras","Botswana",3
        )
        questionsList.add(que3)

        val que4 = Question(
            4,"what country this flag belong to?",
            R.drawable.ic_flag_of_ghana,"Niger","Myanmar",
            "Vietnam","Ghana",4
        )
        questionsList.add(que4)

        val que5 = Question(
            5,"what country this flag belong to?",
            R.drawable.ic_flag_of_liberia,"Liberia","Mali",
            "USA","South Sudan",1
        )
        questionsList.add(que5)

        val que6 = Question(
            6,"what country this flag belong to?",
            R.drawable.ic_flag_of_fijijpg,"New Zealand","Fiji",
            "Djibouti","Great Britain",2
        )
        questionsList.add(que6)

        val que7 = Question(
            7,"what country this flag belong to?",
            R.drawable.ic_flag_of_guatemala,"Chad","Argentina",
            "Guatemala","Botswana",3
        )
        questionsList.add(que7)

        val que8 = Question(
            8,"what country this flag belong to?",
            R.drawable.ic_flag_of_uganda,"Germany","Bahrain",
            "Guatemala","Uganda",4
        )
        questionsList.add(que8)

        val que9 = Question(
            9,"what country this flag belong to?",
            R.drawable.ic_flag_of_haiti,"Maldives","Ethiopia",
            "Haiti","Zimbabwe",3
        )
        questionsList.add(que9)

        val que10 = Question(
            10,"what country this flag belong to?",
            R.drawable.ic_flag_of_montenegro,"Nicaragua","Montenegro",
            "Cape Verde","Serbia",2
        )
        questionsList.add(que10)

        return questionsList
    }

    fun getSuperHardQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,"what country this flag belong to?",
            R.drawable.ic_flag_of_tonga,"Tonga","Fiji",
            "Australia","Austria",1
        )
        questionsList.add(que1)

        val que2 = Question(
            2,"what country this flag belong to?",
            R.drawable.ic_flag_of_bhutan,"Brunei","Bhutan",
            "Laos","Algeria",2
        )
        questionsList.add(que2)

        val que3 = Question(
            3,"what country this flag belong to?",
            R.drawable.ic_flag_of_gabon,"Guyana","Gambia",
            "Gabon","Greece",3
        )
        questionsList.add(que3)

        val que4 = Question(
            4,"what country this flag belong to?",
            R.drawable.ic_flag_of_gambia,"Grenada","Guinea",
            "Gabon","Gambia",4
        )
        questionsList.add(que4)

        val que5 = Question(
            5,"what country this flag belong to?",
            R.drawable.ic_flag_of_cyprus,"Congo","Cyprus",
            "Comoros","Andorra",2
        )
        questionsList.add(que5)

        val que6 = Question(
            6,"what country this flag belong to?",
            R.drawable.ic_flag_of_cook_islands,"Cook Islands","Great Britain",
            "Australia","Nauru",1
        )
        questionsList.add(que6)

        val que7 = Question(
            7,"what country this flag belong to?",
            R.drawable.ic_flag_of_tuvalu,"Marshal Islands","Great Britain",
            "Tuvalu","Cook Islands",3
        )
        questionsList.add(que7)

        val que8 = Question(
            8,"what country this flag belong to?",
            R.drawable.ic_flag_of_namibia,"Mauritius","Mauritania",
            "Congo","Namibia",4
        )
        questionsList.add(que8)

        val que9 = Question(
            9,"what country this flag belong to?",
            R.drawable.ic_flag_of_hong_kong,"Hong Kong","Taiwan",
            "China","Nepal",1
        )
        questionsList.add(que9)

        val que10 = Question(
            10,"what country this flag belong to?",
            R.drawable.ic_flag_of_tajikistan,"Uzbekistan","Tajikistan",
            "Kyrgyszstan","Kazakhstan",2
        )
        questionsList.add(que10)

        return questionsList
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}
