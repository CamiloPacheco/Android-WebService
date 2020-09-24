package com.cacomas.navigationlogin.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.data.Student
import com.cacomas.navigationlogin.viewmodel.LoginViewModel
import com.cacomas.navigationlogin.viewmodel.PostViewModel
import com.cacomas.navigationlogin.viewmodel.StudentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
    var professorId= ""
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
            professorId=courseDetails.get(0).professor.id
        })

        postViewModel.getCourseid().observe(viewLifecycleOwner,Observer { id->
           CourseId=id
        })
        studentViewModel.getStudent().observe(viewLifecycleOwner,Observer { student->
            Log.d("MyOut",  student.country)
            val builder = AlertDialog.Builder(requireActivity())

            var Mesage: String =" Course ID:  "+ student.course_id + "\n" +
                    " Name  :  "+ student.name + "\n" +
                    " UserName:  "+ student.username + "\n" +
                    " Phone:  "+ student.phone + "\n" +
                    " Email:  "+ student.email + "\n" +
                    " City:  "+ student.city + "\n" +
                    " Country:  "+ student.country + "\n" +
                    " Birthday:  "+ student.birthday + "\n"

            builder.setTitle("Details")
            builder.setMessage(Mesage)
            builder.setPositiveButton("OK", null)
            builder.show()
        })
        view.findViewById<FloatingActionButton>(R.id.AddStudent).setOnClickListener {
            val user=loginViewModel.getUsuario().value!!
            val token=loginViewModel.gettoken().value!!
            studentViewModel.addStudent(user,CourseId,token)
        }
        val tv_click_me = view.findViewById(R.id.ProfessorNameTxt) as TextView
        // set on-click listener
        tv_click_me.setOnClickListener {
            // your code to run when the user clicks on the TextView
            val user=loginViewModel.getUsuario().value!!
            val token=loginViewModel.gettoken().value!!
            studentViewModel.showProfessor(user,professorId,token)
        }


    }

    override fun onItemClick(item: Student, position: Int) {
        val user=loginViewModel.getUsuario().value!!
        val token=loginViewModel.gettoken().value!!
        studentViewModel.showStudent(user,item.id,token)
    }


}