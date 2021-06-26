package com.example.foodjurnalapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodjurnalapp.R
import com.example.foodjurnalapp.model.FoodLog
import kotlinx.android.synthetic.main.foodlog_item.view.*

class FoodLogListAdapter(val foodLogList:ArrayList<FoodLog>)
    :RecyclerView.Adapter<FoodLogListAdapter.FoodLogViewHolder>(){
    class FoodLogViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.foodlog_item, parent, false)

        return FoodLogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodLogList.size
    }

    override fun onBindViewHolder(holder: FoodLogViewHolder, position: Int) {
        holder.view.txtFoodName.setText(foodLogList[position].name)
        holder.view.txtFoodCal.setText(foodLogList[position].cal.toString())
    }

    fun updateFoodLogList(newFoodLogList: List<FoodLog>){
        foodLogList.clear()
        foodLogList.addAll(newFoodLogList)
        notifyDataSetChanged()
    }
}