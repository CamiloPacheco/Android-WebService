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
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.viewmodel.LoginViewModel
import com.cacomas.navigationlogin.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    val postViewModel: PostViewModel by activityViewModels()
    private val adapter = PostsAdapter(ArrayList())


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
        var token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE0MiwiaWF0IjoxNjAwNTE5NzI3LCJleHAiOjE2MDA1MTk4NDd9.RkjJeq4cdrLU1wyk7u1MJ2MgX1hZ1EmH5sHNvSw79m0"
        postViewModel.getCourses("elprofesor",token);
        postViewModel.getCoursesData()

        postViewModel.postsLiveData.observe(getViewLifecycleOwner(), Observer {
            adapter.posts.clear()
            adapter.posts.addAll(it)
            adapter.notifyDataSetChanged()
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
            postViewModel.addCourses("elprofesor",token);
            postViewModel.getCoursesData()
        }
    }
}