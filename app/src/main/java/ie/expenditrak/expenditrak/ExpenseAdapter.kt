package ie.expenditrak.expenditrak

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ie.expenditrak.expenditrak.model.Expsense
import kotlinx.android.synthetic.main.expense_list.view.constraintLayout

class ExpenseAdapter(var expenseList:ArrayList<Expsense>, var context: Context,
                     val listener: (Int) -> Unit ): RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(expenseList[position], position,listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        var view:View = LayoutInflater.from(context)
                .inflate(R.layout.expense_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var merchantName = itemView.findViewById<TextView>(R.id.merchant_name)
        var price = itemView.findViewById<TextView>(R.id.price_tag)
        var date = itemView.findViewById<TextView>(R.id.date)

        fun bindView(expense: Expsense, pos:Int, listener:(Int)-> Unit)= with(itemView){
            merchantName.text = "Merchant Name: ${expense.merchant}"
            price.text = "Price €: ${expense.price}"
            date.text = "Date: ${expense.date}"

            constraintLayout.setOnClickListener{listener(pos)}

            //link for clicklistener with kotlin for recyclerviews https://www.andreasjakl.com/recyclerview-kotlin-style-click-listener-android/
        }


    }
}