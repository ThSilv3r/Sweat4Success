package com.example.sweat4success.workout
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sweat4success.R
import com.example.sweat4success.controller.DataController
import com.example.sweat4success.database.UserDb
import com.example.sweat4success.controller.FriendController
import com.example.sweat4success.modell.Account
import com.example.sweat4success.modell.Workouts
import com.example.sweat4success.modell.viewModel.UserViewModel
import com.example.sweat4success.modell.viewModel.WorkoutViewModel
import kotlinx.android.synthetic.main.shareworkout.view.*

class ShareWorkout:AppCompatActivity() {
    private var account: Account = Account();
    private lateinit var userViewModel: UserViewModel
    private lateinit var workoutViewModel: WorkoutViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        workoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        setContentView(R.layout.shareworkout)
    }

    fun setUI() {
        var userList = account.getUserList();
        var username = "";
        var user = userList.find { it.username == username } as UserDb;

    }
    private fun Button.setOnClickListener() {
        val fc: FriendController = FriendController()
        var friendname = friendtosharewith.text;
        var users = userViewModel.getAll();
        var user = users.find{x->x.username==friendname} as UserDb
        var workoutModel=Workouts();
        var workout=workoutViewModel.findByName(workoutModel.getWorkoutname())
        if (fc.isFriend(user,userViewModel)){
            fc.sendWorkout(workout,user.uid,userViewModel,workoutViewModel)
        }
    }

}