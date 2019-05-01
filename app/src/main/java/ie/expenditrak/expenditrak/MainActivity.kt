package ie.expenditrak.expenditrak

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ie.expenditrak.expenditrak.model.Expsense
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var adapter: ExpenseAdapter? = null
    var expenseList: ArrayList<Expsense>? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expenseList = ArrayList<Expsense>()
        setExpenseValues()
        var tesco : Expsense = Expsense("TEsco", "20/01/02", 44.00)
        expenseList!!.add(tesco)
        adapter = ExpenseAdapter(expenseList!!, this)
        layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter
    }

    fun setExpenseValues(){
        var tesco : Expsense = Expsense("TEsco", "20/01/02", 44.00)
        expenseList!!.add(tesco)
        var tesco1 : Expsense = Expsense("Maxol", "20/01/02", 44.00)
        expenseList!!.add(tesco1)
        var tesco2 : Expsense = Expsense("Centra", "20/01/02", 44.00)
        expenseList!!.add(tesco2)
        var tesco3 : Expsense = Expsense("MaxiZoo", "20/01/02", 44.00)
        expenseList!!.add(tesco3)
        var tesco4 : Expsense = Expsense("Pub", "20/01/02", 44.00)
        expenseList!!.add(tesco4)
        var tesco5 : Expsense = Expsense("Holiday", "20/01/02", 44.00)
        expenseList!!.add(tesco5)

    }
}
