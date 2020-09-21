package com.cacomas.navigationlogin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.viewmodel.LoginViewModel
import com.cacomas.navigationlogin.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*



class HomeFragment : Fragment(), PostsAdapter.OnCourseItemClickListner {
    val postViewModel: PostViewModel by activityViewModels()
    private val adapter = PostsAdapter(ArrayList(), this)
    val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().posts_recycler.adapter = adapter
        requireView().posts_recycler.layoutManager = LinearLayoutManager(requireContext())
        val user=loginViewModel.getUsuario().value!!
        val token=loginViewModel.gettoken().value!!
        postViewModel.getCourses(user,token);

        postViewModel.getCourseData().observe(viewLifecycleOwner,Observer { courses ->
            adapter.posts.clear()
            adapter.posts.addAll(courses)
            adapter.notifyDataSetChanged()
        })

        postViewModel.getCourseDetails().observe(viewLifecycleOwner,Observer { courseDetails ->
            if (courseDetails.isNotEmpty())
                Toast.makeText(context, courseDetails.get(0).professor.name , Toast.LENGTH_SHORT).show()
        })

        val navController = findNavController()
        loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
            if (logged == false) {
                navController.navigate(R.id.loginFragment)}
        })

        view.findViewById<Button>(R.id.buttonLogOut).setOnClickListener {
            loginViewModel.setLogged(false)
        }
        view.findViewById<Button>(R.id.Addbutton).setOnClickListener {
            val user=loginViewModel.getUsuario().value!!
            val token=loginViewModel.gettoken().value!!
            postViewModel.addCourses(user,token);
        }
    }
    override fun onItemClick(item: Course, position: Int) {
        val user=loginViewModel.getUsuario().value!!
        val token=loginViewModel.gettoken().value!!
        postViewModel.ShowCourseDetails(user,item.id,token)

    }
}