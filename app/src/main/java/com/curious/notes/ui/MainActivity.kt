package com.curious.notes.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.curious.notes.R
import com.curious.notes.adapters.NoteAdapter
import com.curious.notes.db.Note
import com.curious.notes.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var noteAdapter: NoteAdapter
    private var totalBusinessNotes = 0
    private var totalPersonalNotes = 0
    private var totalPersonalCompletedNotes = 0
    private var totalBusinessCompletedNotes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerview()
        setUpProgressViews()
        subscribeToObservers()
        fabAddNote.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpProgressViews() {
        progressBarBusiness.progressDrawable.setColorFilter(
            Color.parseColor("#FF6200EE"), android.graphics.PorterDuff.Mode.SRC_IN
        )

        progressBarPersonal.progressDrawable.setColorFilter(
            Color.parseColor("#FF018786"), android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    private fun setUpRecyclerview() {
        noteAdapter = NoteAdapter(
            onItemCheckListener = this::onItemCheck,
            onOptionsMenuSelection = ::onOptionMenuSelection
        )
        rvNotes.adapter = noteAdapter
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

    private fun onItemCheck(isChecked: Boolean, note: Note) {
        viewModel.setAsCompletedNote(note.id, isChecked)
        setBusinessProgress(totalBusinessCompletedNotes)
        setPersonalProgress(totalPersonalNotes)
    }

    private fun onOptionMenuSelection(position: Int, note: Note) {
        performOptionsMenuClick(position, note)
    }

    private fun subscribeToObservers() {
        viewModel.allNotes.observe(this, androidx.lifecycle.Observer {
            it?.let {
                noteAdapter.setList(it)
            }
        })

        viewModel.allBusinessTypeNotes.observe(this, androidx.lifecycle.Observer {
            it?.let {
                totalBusinessNotes = it.size
                setTotalNoOfNotesByType("B", it.size)
            }
        })

        viewModel.allPersonalTypeNotes.observe(this, androidx.lifecycle.Observer {
            totalPersonalNotes = it.size
            setTotalNoOfNotesByType("P", it.size)
        })

        viewModel.allBusinessCompletedList.observe(this, androidx.lifecycle.Observer {
            it?.let {
                try {
                    if (it.isNotEmpty()) {
                        totalBusinessCompletedNotes = it.size
                        setBusinessProgress(it.size)
                    } else {
                        progressBarBusiness.progress = 0
                    }
                } catch (e: ArithmeticException) {

                }
            }
        })

        viewModel.allPersonalCompletedList.observe(this, androidx.lifecycle.Observer {
            it?.let {
                try {
                    if (it.isNotEmpty()) {
                        totalPersonalCompletedNotes = it.size
                        setPersonalProgress(it.size)
                    } else {
                        progressBarPersonal.progress = 0
                    }
                } catch (e: ArithmeticException) {

                }
            }
        })
    }

    private fun setPersonalProgress(size: Int) {
        val personalProgress = (size.toDouble() / totalPersonalNotes) * 100
        progressBarPersonal.progress = personalProgress.toInt()
    }

    private fun setBusinessProgress(size: Int) {
        val businessProgress = (size.toDouble() / totalBusinessNotes) * 100
        progressBarBusiness.progress = businessProgress.toInt()
    }

    private fun setTotalNoOfNotesByType(type: String, count: Int) {
        when (type) {
            "B" -> {
                lblTotalBusinessTasks.text = "$count tasks"
            }
            "P" -> {
                lblTotalPersonalTasks.text = "$count tasks"
            }
        }

    }

    private fun performOptionsMenuClick(position: Int, note: Note) {
        // create object of PopupMenu and pass context and view where we want
        // to show the popup menu
        val popupMenu = PopupMenu(this, rvNotes[position].findViewById(R.id.lblNoteOptionsMenu))
        // add the menu
        popupMenu.inflate(R.menu.options_menu)
        // implement on menu item click Listener
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.pmDeleteNote -> {
                        // here are the logic to delete an item from the list
                        viewModel.deleteNote(note)
                        return true
                    }
                    // in the same way you can implement others
                    R.id.pmEditNote -> {
                        // define
                        return true
                    }
                    R.id.pmShareNote -> {
                        // define
                        return true
                    }
                }
                return false
            }
        })
        popupMenu.show()
    }
}