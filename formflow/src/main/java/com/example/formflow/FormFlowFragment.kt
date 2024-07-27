package com.example.formflow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
import android.graphics.Color
import com.example.formflow.field.custome.CustomField
import com.example.formflow.listener.FormFlowListener
import remote.request.FormItem

class FormFlowFragment : Fragment() {

    private var formViewModel: FormViewModel? = null
    private var formFlowListener: FormFlowListener? = null

    // Arguments
    private var formFieldsArgument: ArrayList<Field> = arrayListOf()
    private var submitButtonColor: String = ""

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
            submitButtonColor = it.getString("submit_button_color") ?: ""
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

        // Setup close button
        view.findViewById<ImageButton>(R.id.button_close).setOnClickListener {
            formFlowListener?.onFormClosed()
        }
        formFlowListener = activity as? FormFlowListener
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
                    FieldType.CUSTOM -> {
                        it.customField?.let { customView ->
                            FormBuilder.addCustomField(formContext, formContainer, customView)
                        }

                    }
                }
            }
            addSubmitButton(formContainer)
        }
    }


    private fun addSubmitButton(container: LinearLayout) {
        val submitButton = Button(context).apply {
            text = "Submit"
            if (submitButtonColor.isNotEmpty()) {
                setBackgroundColor(Color.parseColor(submitButtonColor))
            }

            setOnClickListener {
                formViewModel?.sendFormData(
                    collectFormData()
                )

                Toast.makeText(
                    requireContext(),
                    "Your form has been submitted successfully!",
                    Toast.LENGTH_LONG
                ).show()

                formFlowListener?.onFormSubmitted()
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

                    is CustomField -> {
                        answers.add(view.getValue())
                    }
                }
            }
        }
        return FormItem(answers)
    }
}
