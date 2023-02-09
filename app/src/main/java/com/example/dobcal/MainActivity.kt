package com.example.dobcal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvdate : TextView? = null
    private var main : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btndate: Button = findViewById(R.id.btndate)
        tvdate = findViewById(R.id.tvdate)
        main = findViewById(R.id.main)
        btndate.setOnClickListener {
            clickDatePicker()
        }
    }
         fun clickDatePicker(){

             val mycal = Calendar.getInstance()
             val year = mycal.get(Calendar.YEAR)
             val month = mycal.get(Calendar.MONTH)
             val day = mycal.get(Calendar.DAY_OF_MONTH)

             val dpp = DatePickerDialog(this,
                 DatePickerDialog.OnDateSetListener{ view, year, month, dayofmonth ->
                     Toast.makeText(this,
                         "Counted ", Toast.LENGTH_LONG).show()


                     val date = "$dayofmonth/${month+1}/$year"
                     tvdate?.text = date

                     val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                     val thedate = sdf.parse(date)

                     thedate?.let {
                         val selecteddateinminutes = thedate.time / 60000

                         val current = sdf.parse(sdf.format(System.currentTimeMillis()))

                         current?.let {
                             val currentinmin = current.time / 60000

                             val diff = currentinmin - selecteddateinminutes

                             main?.text = diff.toString()
                         }
                     }
                 },
                 year,
                 month,
                 day,
             )

             dpp.datePicker.maxDate = System.currentTimeMillis() - 86400000
             dpp.show()
        }
    }
