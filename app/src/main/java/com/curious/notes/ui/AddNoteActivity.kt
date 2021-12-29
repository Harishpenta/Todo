package com.curious.notes.ui


import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.curious.notes.R
import com.curious.notes.db.Task
import com.curious.notes.ui.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_task.*
import timber.log.Timber
import java.util.*


@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var isBusinessType = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        toolbarAddNote.title = ""
        setSupportActionBar(toolbarAddNote)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        lblSelectionBusiness.setOnClickListener {
            isBusinessType = true
            setSelectedNoteType()
        }
        lblSelectionPersonal.setOnClickListener {
            isBusinessType = false
            setSelectedNoteType()
        }
    }

    private fun setSelectedNoteType() {
        if (isBusinessType) {
            lblSelectionBusiness.setBackgroundColor(Color.parseColor("#FF6200EE"))
            lblSelectionBusiness.setTextColor(Color.WHITE)
            lblSelectionPersonal.setBackgroundColor(Color.parseColor("#FFFFFF"))
            lblSelectionPersonal.setTextColor(Color.BLACK)
        } else {
            lblSelectionBusiness.setBackgroundColor(Color.parseColor("#FFFFFF"))
            lblSelectionBusiness.setTextColor(Color.BLACK)
            lblSelectionPersonal.setBackgroundColor(Color.parseColor("#FF6200EE"))
            lblSelectionPersonal.setTextColor(Color.WHITE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.curious.notes.R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miAddNote -> {
                val enteredTitle = etNote.text.toString()
                val dateTimeLong = Calendar.getInstance().timeInMillis
                val noteDesc = etDescriptionNote.text.toString()
                if (enteredTitle.isNotEmpty()) {
                    val isAdded = addNote(enteredTitle, noteDesc, dateTimeLong)
                    if (isAdded) {
                        Snackbar.make(
                            llMainAddNote,
                            "Note added successfully",
                            Snackbar.LENGTH_LONG
                        ).show()
                        onBackPressed()
                    } else {
                        Snackbar.make(
                            llMainAddNote,
                            "Please enter Something",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addNote(enteredNoteText: String, noteDesc: String, date: Long): Boolean {
        if (enteredNoteText.isEmpty()) {
            Timber.d("Something went wrong")
            return false

        }
        val note = Task(
            enteredNoteText,
            noteDesc,
            date,
            if (isBusinessType) "B" else "P",
            false
        )
        viewModel.addNote(note)
        Timber.d("Saved Successfully")
        return true
    }
}