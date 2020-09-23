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
import com.cacomas.navigationlogin.R
import com.cacomas.navigationlogin.viewmodel.PostViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [StudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentFragment : Fragment() {
    val postViewModel: PostViewModel by activityViewModels()
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
        postViewModel.getCourseDetails().observe(viewLifecycleOwner, Observer { courseDetails ->
            Log.d("MyOut",  courseDetails.get(0).name)
            view.findViewById<TextView>(R.id.CourseNameTxt).setText("Course:  "+courseDetails.get(0).name)
            view.findViewById<TextView>(R.id.ProfessorNameTxt).setText("Name:  " + courseDetails.get(0).professor.name)
            view.findViewById<TextView>(R.id.ProfessorEmailTxt).setText("Email:  "+courseDetails.get(0).professor.email)
        })

    }

}