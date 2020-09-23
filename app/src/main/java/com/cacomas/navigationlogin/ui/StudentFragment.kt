package com.cacomas.navigationlogin.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.data.Course
import com.cacomas.navigationlogin.data.Student
import com.cacomas.navigationlogin.viewmodel.LoginViewModel
import com.cacomas.navigationlogin.viewmodel.PostViewModel
import com.cacomas.navigationlogin.viewmodel.StudentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_student.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [StudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentFragment : Fragment(),StudentListAdapter.OnStudentItemClickListner {
    private val studentAdapter = StudentListAdapter(ArrayList(), this)
    val postViewModel: PostViewModel by activityViewModels()
    val loginViewModel: LoginViewModel by activityViewModels()
    val studentViewModel: StudentViewModel by activityViewModels()
    var CourseId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireView().Students_Recycler.adapter = studentAdapter
        requireView().Students_Recycler.layoutManager = LinearLayoutManager(requireContext())

        postViewModel.getCourseDetails().observe(viewLifecycleOwner, Observer { courseDetails ->
            Log.d("MyOut",  courseDetails.get(0).name)
            studentAdapter.students.clear()
            studentAdapter.students.addAll(courseDetails.get(0).students)
            studentAdapter.notifyDataSetChanged()
            view.findViewById<TextView>(R.id.CourseNameTxt).setText("Course:  "+courseDetails.get(0).name)
            view.findViewById<TextView>(R.id.ProfessorNameTxt).setText("Name:  " + courseDetails.get(0).professor.name)
            view.findViewById<TextView>(R.id.ProfessorEmailTxt).setText("Email:  "+courseDetails.get(0).professor.email)
        })

        postViewModel.getCourseid().observe(viewLifecycleOwner,Observer { id ->
           CourseId=id
        })
        view.findViewById<FloatingActionButton>(R.id.AddStudent).setOnClickListener {
            val user=loginViewModel.getUsuario().value!!
            val token=loginViewModel.gettoken().value!!
            studentViewModel.addStudent(user,CourseId,token)
        }

    }

    override fun onItemClick(item: Student, position: Int) {
        TODO("Not yet implemented")
    }


}