package ie.expenditrak.expenditrak

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ie.expenditrak.expenditrak.model.Expsense
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    var database: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    var expenseList: ArrayList<Expsense>? = null
    var adapter: ExpenseAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    //firebase link https://firebase.google.com/docs/database/android/start

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        database = FirebaseDatabase.getInstance()
        reference = database!!.getReference("expense")
        expenseList = ArrayList<Expsense>()
        getExpenses()
        addExpense.setOnClickListener{
            var amount = 0.0
            for (i in expenseList!!.iterator()){
                amount += i.price!!
            }
            var amountTotal:Double = String.format("%.2f",amount).toDouble()
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("You total expenses is €$amountTotal")
                    .setPositiveButton("OK", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.cancel()
                    })
            val showDialog = dialog.create()
            showDialog.setTitle("Total Expenses")
            dialog.show()

            //Link for alertdialogs with kotlin https://www.tutorialkart.com/kotlin-android/android-alert-dialog-example/


        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.add -> {
                startActivity(Intent(applicationContext, AddExpense::class.java))
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }



    fun getExpenses(){
        database!!.reference.child("expense").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()){
                    expenseList!!.clear()//Clear list so no duplicates are made
                    for(expense in dataSnapshot.children) {
                        val myExpsense = expense.getValue(Expsense::class.java)!!
                        expenseList!!.add(myExpsense)
                        //deleteExpense(myExpsense.id)
                    }
                    adapter = ExpenseAdapter(expenseList!!, applicationContext){row ->
                        Log.i("DELETE===>>>", "${expenseList!![row].id!!}")
                        deleteExpense(expenseList!![row].id!!)
                    }
                    layoutManager = LinearLayoutManager(applicationContext)
                    recyclerview.layoutManager = layoutManager
                    recyclerview.adapter = adapter
                    adapter!!.notifyDataSetChanged()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(applicationContext, "Something went wrong could not retrive data", Toast.LENGTH_LONG).show()

            }
        })
    }

    fun deleteExpense(id:String ){
        val removeExp = database!!.getReference("expense").child(id)
        AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure you want to delete this item")
                .setPositiveButton("Yes", object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        removeExp.removeValue()
                    }
                })
                .setNegativeButton("No", null)
                .show()
        adapter!!.notifyDataSetChanged()
    }


}
