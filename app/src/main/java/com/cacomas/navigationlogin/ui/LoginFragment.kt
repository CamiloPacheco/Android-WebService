package com.cacomas.navigationlogin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.viewmodel.LoginViewModel



class LoginFragment : Fragment() {
    val loginViewModel: LoginViewModel by activityViewModels()
    var theToken : String = ""
    var userName : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
            if (logged == true) {
                navController.navigate(R.id.homeFragment)}
        })

        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            val usuario : String =  view.findViewById<TextView>(R.id.editTextTextPersonName).text.toString()
            val clave : String = view.findViewById<TextView>(R.id.editTextTextPersonName2).text.toString()
            val email : String = view.findViewById<TextView>(R.id.editTextTextPersonName3).text.toString()
            val navController = findNavController()

            loginViewModel.signIn(email,clave,usuario).observe(getViewLifecycleOwner(), Observer { user ->

                Log.d("MyOut", "Fragment  signIn " + user + " error " + user.error)
                theToken = user.token
                userName = user.name
                loginViewModel.settoken(theToken)
                loginViewModel.setuser(userName)

                if (user.token != "") {
                    //Toast.makeText(context, "Token22 " + user.token, Toast.LENGTH_LONG).show()
                    loginViewModel.setLogged(true)
                    navController.navigate(R.id.homeFragment)
                } else {
                   //Toast.makeText(context, "Token22 failure " + user.error, Toast.LENGTH_LONG).show()
                }

            })
        }

        view.findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.signUpFragment)
        }

    }
}