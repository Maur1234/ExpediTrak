package ie.expenditrak.expenditrak

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ie.expenditrak.expenditrak.model.Expsense
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.add_expense.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddExpense : AppCompatActivity() {

    lateinit var mdatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var calendar:Calendar
    var name: String? = null
    var amountSpent: Double = 0.0
    var date: String? = null
    var id: Long = 1L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_expense)
        toolbar.title = "Add Expense"
        setSupportActionBar(toolbar)
        mdatabase = FirebaseDatabase.getInstance()
        reference = mdatabase.getReference("expense")

        //link for date picker https://www.tutorialkart.com/kotlin-android/android-datepicker-kotlin-example/
        calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener{view, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            val dateFormat = "dd/MM/yyyy"
            val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.ENGLISH)
            merchant_dateET.text = simpleDateFormat.format(calendar.time)
        }

        merchant_dateBtn.setOnClickListener {
            it.visibility = View.GONE
            DatePickerDialog(this@AddExpense, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            merchant_dateET.visibility = View.VISIBLE

        }

        addExpenseBtn.setOnClickListener {
            if(!merchant_nameET.text.toString().equals("")) {
                name = merchant_nameET.text.toString()
                amountSpent = merchant_priceET.text.toString().toDouble()
                date =  merchant_dateET.text.toString()


                var id = mdatabase.getReference("expense").push().key
                var expense: Expsense = Expsense(id!!, name!!, date!!, amountSpent)
                reference.child(id).setValue(expense)
            }
            finish()
        }

    }


}