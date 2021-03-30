package com.example.sweat4success.workout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweat4success.R
import com.example.sweat4success.database.ExerciseDb
import com.example.sweat4success.database.WorkoutDb
import kotlinx.android.synthetic.main.workout_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var workoutList = emptyList<WorkoutDb>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.workout_row, parent, false))
    }
    override fun getItemCount(): Int {
        return workoutList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = workoutList[position]
        holder.itemView.titleExercise.text = currentItem.title.toString()
    }

    fun setData(workout: List<WorkoutDb>){
        this.workoutList = workout;
        notifyDataSetChanged();
    }
}