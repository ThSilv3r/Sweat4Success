package com.example.sweat4success.workout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweat4success.R
import com.example.sweat4success.account.CreateAccount
import com.example.sweat4success.account.EditAccount
import com.example.sweat4success.modell.viewModel.ExerciseViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.workout_fragment_list.view.*

class WorkoutListFragment : Fragment() {

    private lateinit var mWorkoutViewModel: WorkoutViewModel;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.workout_fragment_list , container, false);

        val adapter = ListAdapter();
        val recyclerView = view.recyclerView;
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(requireContext());

        mWorkoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java);
        mWorkoutViewModel.readAllData.observe(viewLifecycleOwner, Observer { workout -> adapter.setData(workout) })

        view.goToCreateWorkout.setOnClickListener{
            findNavController().navigate(R.id.action_workoutListFragment_to_addWorkout)
        }

        view.goToProfil.setOnClickListener{
            startActivity(Intent(requireContext(), EditAccount::class.java))
        }
        return view;
    }
}