package com.cacomas.navigationlogin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.data.User
import com.cacomas.navigationlogin.viewmodel.SignUpViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    val signupViewModel : SignUpViewModel by activityViewModels()
    private var userList = mutableListOf<User>()
    var count : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        signupViewModel.getUsers().observe(getViewLifecycleOwner(), Observer { users ->
            run {
                count  = users.size
                userList = users as MutableList<User>
                users.forEach{ user ->
                    Log.d("VideoViewModel", user.name + "  "+ user.pass)
                }
                Log.d("VideoViewModel", "" + users.size)
            }
        })
        view.findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            val user = view.findViewById<TextView>(R.id.TextUserName)
            val pass = view.findViewById<TextView>(R.id.TextPassName)
            signupViewModel.addUser(User(user.text.toString(), pass.text.toString()))
            navController.navigate(R.id.loginFragment)
        }
    }

}