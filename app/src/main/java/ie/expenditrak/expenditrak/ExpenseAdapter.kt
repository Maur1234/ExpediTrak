package ie.expenditrak.expenditrak

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ie.expenditrak.expenditrak.model.Expsense

class ExpenseAdapter(var expenseList:ArrayList<Expsense>, var context: Context): RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(expenseList[position], position)
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
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bindView(expense: Expsense, pos:Int){
            merchantName.text = "Merchant Name: ${expense.merchant}"
            price.text = "Price: ${expense.price}"
        }


    }
}