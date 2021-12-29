package com.curious.todo.adapters

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.curious.todo.R
import com.curious.todo.db.Task
import com.curious.todo.utils.TasksUtility
import kotlinx.android.synthetic.main.item_task.view.*
import timber.log.Timber

class TaskAdapter(
    private val onItemCheckListener: (isChecked: Boolean, task: Task) -> Unit,
    private val onOptionsMenuSelection: (position: Int, task: Task) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.NoteViewHolder>() {
    private var selectedPos = RecyclerView.NO_POSITION
    private val noteList = ArrayList<Task>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setList(artists: List<Task>) {
        noteList.clear()
        noteList.addAll(artists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_task,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        Timber.d(note.toString())
        holder.itemView.apply {
            lblTitle.text = note.title
            lblNoteDate.text = TasksUtility.convertMillisIntoDateString(note.createdDate)
            lblNoteDesc.text = note.description
            if (note.isCompleted) {
                lblTitle.setTextColor(Color.parseColor("#C2C0C0"))
                lblNoteDesc.setTextColor(Color.parseColor("#C2C0C0"))
                lblTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                lblTitle.setTextColor(Color.parseColor("#000000"))
                lblNoteDesc.setTextColor(Color.parseColor("#000000"))
                lblTitle.paintFlags = lblTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            checkb.setOnCheckedChangeListener(null)
            checkb.isChecked = note.isCompleted
            checkb.setOnCheckedChangeListener { _, isChecked ->
                note.isCompleted = isChecked
                onItemCheckListener.invoke(isChecked, note)
            }
            lblNoteOptionsMenu.setOnClickListener {
                it?.let {
                    onOptionsMenuSelection.invoke(position, note)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}