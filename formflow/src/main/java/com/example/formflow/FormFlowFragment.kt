package com.example.formflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class FormFlowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formflow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val answer1: EditText = view.findViewById(R.id.answer1)
        val answer2: EditText = view.findViewById(R.id.answer2)
        val answer3: EditText = view.findViewById(R.id.answer3)
        val submitButton: Button = view.findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            val answer1Text = answer1.text.toString()
            val answer2Text = answer2.text.toString()
            val answer3Text = answer3.text.toString()

            // For now, just show a toast with the answers
            Toast.makeText(
                requireContext(),
                "Answers:\n1. $answer1Text\n2. $answer2Text\n3. $answer3Text",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
