package eu.tutorials.quizapp.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.adapter.QuizResultsAdapter
import eu.tutorials.quizapp.database.DatabaseHandler
import eu.tutorials.quizapp.models.QuizAppModel

class SwipeDeleteCallback(private val context: Context, private val adapter: QuizResultsAdapter, private val quizList: ArrayList<QuizAppModel>, private val databaseHandler: DatabaseHandler) : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

    private val paint = Paint()
    private val trashIcon: Drawable = ContextCompat.getDrawable(context, R.drawable.baseline_delete_24)!!

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false // We don't need to support move here
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val item = quizList[position]

        //deletes from database
        val result = databaseHandler.deleteQuizResult(item.id)
        if (result > 0) {
            quizList.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        val itemView = viewHolder.itemView

        if (dX > 0) {
            val background = Color.parseColor("#FF3D00")
            c.drawRect(
                itemView.left.toFloat(),
                itemView.top.toFloat(),
                itemView.left + dX,
                itemView.bottom.toFloat(),
                paint.apply { color = background }
            )
        }

        if (dX > 0) {
            val iconMargin = (itemView.height - trashIcon.intrinsicHeight) / 2
            val iconLeft = itemView.left + iconMargin
            val iconTop = itemView.top + iconMargin
            val iconRight = itemView.left + iconMargin + trashIcon.intrinsicWidth
            val iconBottom = itemView.bottom - iconMargin
            trashIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            trashIcon.draw(c)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}
