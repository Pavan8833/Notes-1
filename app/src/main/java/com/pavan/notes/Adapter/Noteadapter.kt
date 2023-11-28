package com.pavan.notes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.pavan.notes.Entities.notes

class Noteadapter(
    val context: Context,
    val ondelclick: Notedeleteclickinterface,
    val onnoteclick:Noteclickinterface
) : RecyclerView.Adapter<Noteadapter.Noteviewholder>() {

       private val arrlist = ArrayList<notes>()

   inner class Noteviewholder(itemView: View): RecyclerView.ViewHolder(itemView) {

       val notetitle = itemView.findViewById<TextView>(R.id.notettile)
       val timestamp = itemView.findViewById<TextView>(R.id.timestamp)
       val delete = itemView.findViewById<ImageView>(R.id.deleteicon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Noteviewholder {
            val ItemView = LayoutInflater.from(parent.context).inflate(R.layout.noteitem,parent,false)
        return Noteviewholder(ItemView)
    }

    override fun getItemCount(): Int {
         return arrlist.size
    }

    override fun onBindViewHolder(holder: Noteviewholder, position: Int) {
        holder.notetitle.setText(arrlist.get(position).title)
        holder.timestamp.setText("Last updated:"+arrlist.get(position).date)
        holder.delete.setOnClickListener{
            onnoteclick.onnoteclick(arrlist.get(position))
        }
        holder.itemView.setOnClickListener {
            ondelclick.onDeleteiconitem(arrlist.get(position))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newlist : List<notes>){
        arrlist.clear()
        arrlist.addAll(newlist)
        notifyDataSetChanged()
    }
}

interface Noteclickinterface {
    fun onnoteclick(note: notes)
}

interface Notedeleteclickinterface {
    fun onDeleteiconitem(note: notes)
}