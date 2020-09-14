package com.cacomas.navigationlogin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.viewmodel.LoginViewModel



// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    val loginViewModel: LoginViewModel by activityViewModels()
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
            loginViewModel.setLogged(true)
        }
        view.findViewById<Button>(R.id.buttonSignUp).setOnClickListener {

                navController.navigate(R.id.signUpFragment)
        }

    }
}