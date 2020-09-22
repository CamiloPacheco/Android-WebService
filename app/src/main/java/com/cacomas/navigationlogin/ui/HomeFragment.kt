package com.cacomas.navigationlogin.ui

import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.viewmodel.LoginViewModel
import com.cacomas.navigationlogin.viewmodel.PostViewModel
import com.cacomas.navigationlogin.viewmodel.StudentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.view.*



class HomeFragment : Fragment(), PostsAdapter.OnCourseItemClickListner {
    val postViewModel: PostViewModel by activityViewModels()
    private val adapter = PostsAdapter(ArrayList(), this)
    val loginViewModel: LoginViewModel by activityViewModels()
    val studentViewModel: StudentViewModel by activityViewModels()
    var itemID : String = ""
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
            val builder = AlertDialog.Builder(requireActivity())
            val negativeButtonClick = { dialog: DialogInterface, which: Int ->AddStudent(user,itemID,token)
            }
            var Mesage: String =""
            var PMesage: String = "Professor Details: \n   name:  "+ courseDetails.get(0).professor.name+"\n"+
                    "   UserName:  " + courseDetails.get(0).professor.username+"\n"+
                    "   email:  " + courseDetails.get(0).professor.email+"\n\n"
            for(i in 0..courseDetails.get(0).students.size-1) {
                 Mesage =Mesage+" Student Details \n  name:  " + courseDetails.get(0).students[i].name+"\n"+
                        "   UserName:  " + courseDetails.get(0).students[i].username+"\n"+
                        "   email:  " + courseDetails.get(0).students[i].email+"\n\n"
            }
            builder.setTitle("Course Details")
            builder.setMessage(PMesage +  Mesage)
            builder.setPositiveButton("OK", null)
            builder.setNegativeButton("Add Student",negativeButtonClick )
            builder.show()
        })

        val navController = findNavController()
        loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
            if (logged == false) {
                navController.navigate(R.id.loginFragment)}
        })

        view.findViewById<FloatingActionButton>(R.id.buttonLogOut).setOnClickListener {
            loginViewModel.setLogged(false)
        }
        view.findViewById<FloatingActionButton>(R.id.Addbutton).setOnClickListener {
            val user=loginViewModel.getUsuario().value!!
            val token=loginViewModel.gettoken().value!!
            postViewModel.addCourses(user,token);
        }
        view.findViewById<FloatingActionButton>(R.id.resetBtn).setOnClickListener {
            val user=loginViewModel.getUsuario().value!!
            val token=loginViewModel.gettoken().value!!
            postViewModel.deleteCourses(user, token)
        }
    }
    override fun onItemClick(item: Course, position: Int) {
        val user=loginViewModel.getUsuario().value!!
        val token=loginViewModel.gettoken().value!!
        itemID=item.id
        postViewModel.ShowCourseDetails(user,item.id,token)

    }
    fun AddStudent(db_id:String, course_id:String,token:String) =studentViewModel.addStudent(db_id,course_id,token)
}