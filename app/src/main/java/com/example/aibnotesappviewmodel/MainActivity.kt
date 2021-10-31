package com.example.aibnotesappviewmodel

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity()
{

    private lateinit var RV: RecyclerView
    private lateinit var RVA: RecyclerViewAdapter
    private lateinit var EDT: EditText
    private lateinit var BTN: Button
    private lateinit var notes: List<MyNote>
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RV = findViewById(R.id.noteRv)
        EDT = findViewById(R.id.edtnote)
        BTN = findViewById(R.id.btnnote)
        notes = listOf()


        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getNotes().observe(this, { notes -> RVA.update(notes) })



        BTN.setOnClickListener {
            if(EDT.text.isNotBlank())
            {
                mainViewModel.addNote(EDT.text.toString())
                EDT.text.clear()
                EDT.clearFocus()
            }
            else
            {
                Toast.makeText(applicationContext,"Pls Enter Note", Toast.LENGTH_LONG).show()
            }

        }

        RVA = RecyclerViewAdapter(this, notes )
        RV.adapter = RVA
        RV.layoutManager = LinearLayoutManager(this)
    }

    fun raiseDialog(id: Int)
    {
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"
        dialogBuilder

            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {

                    _, _ -> mainViewModel.editNote(id, updatedNote.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }
}