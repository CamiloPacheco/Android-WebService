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


class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    val signupViewModel : SignUpViewModel by activityViewModels()
    private var userList = mutableListOf<User>()
    var theToken : String = ""

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

        view.findViewById<Button>(R.id.crearbtn).setOnClickListener {

            val user = view.findViewById<TextView>(R.id.userTextField).text.toString()
            val pass = view.findViewById<TextView>(R.id.passwordTextField).text.toString()
            val email = view.findViewById<TextView>(R.id.emailTextField).text.toString()

            signupViewModel.signUp(email,pass, user).observe(getViewLifecycleOwner(), Observer { user ->

                Log.d("MyOut", "Fragment  signUp " + user + " error " + user.error)
                theToken = user.token

            })

            navController.navigate(R.id.loginFragment)
        }
    }

}