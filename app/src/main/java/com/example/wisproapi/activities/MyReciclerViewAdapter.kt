package com.example.wisproapi.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wisproapi.R
import com.example.wisproapi.retrofit_models.PaymentObject


class MyRecyclerViewAdapter internal constructor(
    context: Context?,
    data: List<PaymentObject>
) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    private val mData: List<PaymentObject> = data
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mClickListener: ItemClickListener? = null
    private var mExpandedPosition: Int = -1
    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = mInflater.inflate(R.layout.reciclerview_row, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val payment = mData.get(position)
        holder.client_name.text = payment.client_name
        holder.ammount.text = "$" + payment.amount
        holder.transaction_kind.text = payment.transaction_kind
        holder.payment_date.text = payment.payment_date?.substring(0,10)
        holder.details.text = payment.payment_date?.substring(0,10)

        val isExpanded = position === mExpandedPosition
        holder.details.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)
        holder.itemView.isActivated = isExpanded
        holder.itemView.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(position)
        }
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var client_name: TextView = itemView.findViewById(R.id.client_name)
        var ammount: TextView = itemView.findViewById(R.id.ammount)
        var transaction_kind: TextView = itemView.findViewById(R.id.transaction_kind)
        var payment_date: TextView = itemView.findViewById(R.id.payment_date)
        var details: TextView = itemView.findViewById(R.id.details)
        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return mData.get(id).toString()
    }

    // allows clicks events to be caught

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

}