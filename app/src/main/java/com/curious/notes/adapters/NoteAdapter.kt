package com.curious.notes.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.curious.notes.R
import com.curious.notes.db.Note
import com.curious.notes.utils.NotesUtility
import kotlinx.android.synthetic.main.item_note.view.*
import timber.log.Timber

class NoteAdapter(
    private val onItemCheckListener: (isChecked: Boolean, note: Note) -> Unit,
    private val onOptionsMenuSelection: (position: Int, note: Note) -> Unit
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var selectedPos = RecyclerView.NO_POSITION
    private val noteList = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setList(artists: List<Note>) {
        noteList.clear()
        noteList.addAll(artists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_note,
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
//            if (note.type == "B") {
//                lblNoteType.text = "B"
//                lblNoteType.setBackgroundResource(R.drawable.business_circle_shape)
//            } else {
//                lblNoteType.text = "P"
//                lblNoteType.setBackgroundResource(R.drawable.personal_circle_shape)
//            }
            lblNoteDate.text = NotesUtility.convertMillisIntoDateString(note.createdDate)
            if (note.isCompleted) {
                lblTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
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