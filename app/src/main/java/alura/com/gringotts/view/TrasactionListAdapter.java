package alura.com.gringotts.view;

import android.view.View;

import java.util.List;

import alura.com.gringotts.data.model.Transaction;

class TrasactionListAdapter(private val transaction :List<Transaction>) :
        RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>(){

        override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):TransactionViewHolder{
        val view=LayoutInflater.from(parent.context)
        .inflate(R.layout.transaction_item,parent,false)
        return TransactionViewHolder(view)
        }

        override fun onBindViewHolder(holder:TransactionViewHolder,position:Int){
        holder.bind(transaction[position])
        }

        override fun getItemCount():Int{
        return benefits.size
        }

class TransactionViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){

        }
        }