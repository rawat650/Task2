package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity(), Adapter.ClickInterface {

    var list = arrayListOf<CardInfo>()
    lateinit var text1: TextView
    lateinit var recycler_view: RecyclerView
    lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initAdapter()
    }

    private fun initViews() {
        text1 = findViewById<TextView>(R.id.text1);
        text1.setOnClickListener {
            openDialog()
        }
    }

    private fun initAdapter() {
        recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        adapter = Adapter(arrayListOf(), this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun add(item: android.view.MenuItem) {
        openDialog()
    }

    fun openDialog() {
        val View = View.inflate(this, R.layout.custom_dialog, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(View)
        val dialog = builder.create()
        val etDate = View.findViewById<EditText>(R.id.etDate)
        val etTitle = View.findViewById<EditText>(R.id.etTitle)
        val btnSave = View.findViewById<Button>(R.id.btnSave)

        btnSave?.setOnClickListener {
            if (validate(etDate, etTitle)) {
                // value list
                list.add(CardInfo(etTitle?.text.toString(), etDate?.text.toString()))
                adapter.addItems(CardInfo(etTitle?.text.toString(), etDate?.text.toString()))
                if (list.isNotEmpty()) {
                    text1.visibility = android.view.View.GONE
                    recycler_view.visibility = android.view.View.VISIBLE
                }
                dialog.dismiss()
            }
        }
        dialog.show()
        etDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    etDate?.setText("$mDay/$mMonth/$mYear")

                },
                year,
                month,
                day
            )
            datePicker.show()
        }

    }

    fun validate(etDate: EditText?, etTitle: EditText?): Boolean {
        if (etTitle?.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Title", Toast.LENGTH_SHORT).show()
            return false
        } else if (etDate?.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Date", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun OnClick(item: CardInfo){
        val intent = Intent(this,FourthActivity::class.java)
        intent.putExtra("main",item.title)
        intent.putExtra("date",item.date)
        startActivity(intent)

    }

}