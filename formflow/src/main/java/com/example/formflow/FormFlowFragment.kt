package com.example.formflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.formflow.di.FormViewModelFactory
import com.example.formflow.di.ServiceLocator
import com.example.formflow.field.Field
import com.example.formflow.field.FieldType
import com.example.formflow.field.FormBuilder
import com.example.formflow.field.GoogleSheetURL
import remote.request.FormItem

class FormFlowFragment : Fragment() {

    private var formViewModel: FormViewModel? = null

    // Arguments
    private var formFieldsArgument: ArrayList<Field> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formflow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Retrieve arguments
        arguments?.let {
            formFieldsArgument = it.getParcelableArrayList("formFields") ?: arrayListOf()
        }

        val formContext = context ?: requireContext()
        if (GoogleSheetURL.getBaseUrl(formContext).isNullOrBlank().not()) {
            formViewModel = ViewModelProvider(
                this,
                FormViewModelFactory(
                    ServiceLocator.provideAppRepository(
                        baseUrl = GoogleSheetURL.getBaseUrl(formContext) ?: ""
                    )
                )
            )[FormViewModel::class.java]
        }

        if (formFieldsArgument.isNotEmpty()) {
            addFormFields(formFields = formFieldsArgument)
        }
    }

    private fun addFormFields(formFields: List<Field>) {
        val formContext = context ?: requireContext()
        val formContainer = view?.findViewById<LinearLayout>(R.id.form_container)
        formContainer?.removeAllViews()
        formContainer?.let {
            formFields.forEach {
                when (it.type) {
                    FieldType.RADIO -> FormBuilder.addRadioButton(formContext, formContainer, it)
                    FieldType.DROPLIST -> FormBuilder.addDropList(formContext, formContainer, it)
                    FieldType.EDITTEXT -> FormBuilder.addEditText(formContext, formContainer, it)
                }
            }
            addSubmitButton(formContainer)
        }
    }


    private fun addSubmitButton(container: LinearLayout) {
        val submitButton = Button(context).apply {
            text = resources.getString(R.string.submit_button)
            setOnClickListener {
                formViewModel?.sendFormData(
                    collectFormData()
                )

                Toast.makeText(
                    requireContext(),
                    "Answers",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        container.addView(submitButton)
    }

    private fun collectFormData(): FormItem {
        val formContainer = view?.findViewById<LinearLayout>(R.id.form_container)
        val answers: ArrayList<String> = arrayListOf()

        formContainer?.let { container ->
            for (i in 0 until container.childCount) {
                when (val view = container.getChildAt(i)) {
                    is RadioGroup -> {
                        val selectedId = view.checkedRadioButtonId
                        if (selectedId != -1) {
                            val radioButton = view.findViewById<RadioButton>(selectedId)
                            answers.add(radioButton.text.toString())
                        }
                    }

                    is Spinner -> {
                        val selectedItem = view.selectedItem as? String
                        if (selectedItem != null) {
                            answers.add(selectedItem)
                        }
                    }

                    is EditText -> {
                        answers.add(view.text.toString())
                    }
                }
            }
        }
        return FormItem(answers)
    }
}
