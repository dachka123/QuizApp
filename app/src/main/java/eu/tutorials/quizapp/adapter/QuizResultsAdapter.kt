package eu.tutorials.quizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.models.QuizAppModel

class QuizResultsAdapter(
    private var quizList: ArrayList<QuizAppModel>
): RecyclerView.Adapter<QuizResultsAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnclickListener(listener: OnItemClickListener){
        mListener = listener
    }

    class ViewHolder(view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view){
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDifficulty: TextView = view.findViewById(R.id.tvDifficulty)
        val tvResult: TextView = view.findViewById(R.id.tvResult)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizResultsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quiz_app, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: QuizResultsAdapter.ViewHolder, position: Int) {
        val quiz = quizList[position]
        holder.tvTitle.text = quiz.name
        holder.tvDifficulty.text = quiz.difficulty
        holder.tvResult.text = "Score: ${quiz.result}"
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    fun updateQuizList(newQuizList: ArrayList<QuizAppModel>) {
        quizList = newQuizList
        notifyDataSetChanged()
    }
}