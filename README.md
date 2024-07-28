Form library is an Android library designed to simplify the creation of customizable and dynamic forms within your application. 
This library allows developers to effortlessly create forms with various field types, including text fields, radio buttons, dropdown lists, and custom fields. 
Form also provides the capability to submit collected form data to Google Sheets, making it a versatile tool for applications that require data collection and submission.

**Key Features**
- Support for multiple field types: EditText, Radio Buttons, Dropdown Lists, and Custom Fields.
- Customizable field properties, including labels, colors, and mandatory flags.
- Automatic data collection and submission to Google Sheets endpoints.
- Support for creating custom fields to meet specific requirements.

-------------------------------------

**Installation**

To add Form library to your Android project, you need to include it as a dependency using JitPack. Follow these steps to set it up:

**Add JitPack to your root build.gradle file:**

`allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}`

**Add the dependency to your module's build.gradle file:**

```kotlin
implementation("com.github.lamiaomar:FormLibrary:2.1.0")

// Core
implementation("androidx.core:core-ktx:1.10.1")

// Moshi
implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    
// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.11.0")
    
// Retrofit with Moshi Converter
implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

// ViewModel and LiveData
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

```


**Integrate with Google Sheets:**
- Open your Google Sheets document.
- Go to Extensions > Apps Script.
- Add the following code to your Google Sheets script:
```
function doPost(e) {
    var sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
    var data = JSON.parse(e.postData.contents);

    Logger.log('Received data: ' + JSON.stringify(data));

    // Check if the data is an array
    if (Array.isArray(data.data)) {
        // Convert the List<String> to an array of strings
        var row = data.data.map(String);  // Ensure all elements are strings
        Logger.log('Appending row: ' + JSON.stringify(row));
        sheet.appendRow(row);
    } else {
        // Handle the case where the data is not an array
        return ContentService.createTextOutput(
            JSON.stringify({result: "Error", message: "Invalid data format"})
        ).setMimeType(ContentService.MimeType.JSON);
    }

    return ContentService.createTextOutput(
        JSON.stringify({result: "Success"})
    ).setMimeType(ContentService.MimeType.JSON);
}
```
- Deploy the script as a web app.
- Set up your app to use the Google Sheets URL.
```
GoogleSheetURL.saveBaseUrl(
    this,
    "YOUR_GOOGLE_SHEET_WEB_APP_URL"
)
```

> [!NOTE]
> Make sure to set the full URL for the Google Sheets script, including the trailing `/` but excluding the exec word. For example, your URL should look like this: https://script.google.com/macros/s/your-script-id/ without exec at the end.

-------------------------------------

**Usage**
To create different types of form fields, use `Field` class and its Properties:

- type: The type of the field (EDITTEXT, RADIO, DROPLIST, CUSTOM).
- question: The question or label for the field.
- color: The color for the field UI element.
- isMandatory: Boolean indicating if the field is mandatory.
- radioList and dropList: Lists of options for radio button and dropdown fields, respectively.

  Example of createion field:
  ```
  val radioField = Field(
  type = FieldType.RADIO,
  question = "How did you hear about the event?",
  color = "#b26691",
  isMandatory = true,
  radioList = arrayListOf("Friends", "Social Media", "Website", "Other"))
  ```

*Custom Fields*

To create and integrate custom fields, you need to use CustomField interface

Example of a Custom Field Implementation:

```
class CustomRatingField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), CustomField {

    private val ratingBar: RatingBar
    private val label: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_rating_field_layout, this, true)
        ratingBar = view.findViewById(R.id.custom_field_rating)
        label = view.findViewById(R.id.custom_field_label)
    }

    override fun getValue(): String {
        return ratingBar.rating.toString()
    }

    override fun getView(): View {
        return this
    }
}
```
> [!NOTE]
> To customize your forms with various custom fields, you need to implement `getValue()` and `getView()`.

-------------------------------------

**Submitting Form Data** 

***Collecting Data:***

Form library provides an easy way to collect data from the form fields. The data from each field is gathered and processed when the form is submitted.

***Explanation of How to Collect Form Data:*** 

The collectFormData() function is responsible for gathering data from each form field. It traverses through all the form fields, collects their values, and returns them as a FormItem object.

***How to Handle Form Data in the App:***

When the form is submitted, you can handle the collected data within your app by defining appropriate actions in the form submission process.

In the `FormFlowFragment`, the `onFormClosed()` and `onFormSubmitted()` methods are part of the interface for handling form actions. These methods provide hooks for responding to form events, such as when the form is closed or submitted.

***Sending Data***

Once the data is collected from the form fields, it can be sent to a Google Sheet or other endpoints for storage and processing.

------------------------------------

**Troubleshooting**

1- Form Data Not Submitting

Solution: Ensure the Google Sheet URL is correctly set in your app. Check if the Google Sheets script is deployed properly and has the necessary permissions.

2- Custom Fields Not Displaying

Solution: Verify that your custom field implementation properly overrides `getView()` and `getValue()`. Ensure the custom field is added to the form configuration correctly.

3- Google Sheets Script Errors

Solution: Make sure the script is correctly added to your Google Sheet and deployed as a web app. Review the script for any syntax errors or permission issues.


------------------------------------

**Examples**

Example Project Using Form library:

To see a practical implementation of the Form library, you can refer to our example project available on GitHub. This project demonstrates how to set up, customize, and use the Form library in a real-world scenario.

GitHub Repository: [FormsApplication](https://github.com/lamiaomar/FormApplication)

In this example project, you will find:

A complete setup of the FormFlow library.
Examples of creating different types of form fields.
Custom field implementations.
Styling and customization of form fields and submit buttons.
Integration with Google Sheets for form data submission.


Feel free to clone the repository, explore the code, and use it as a reference for your own projects.

------------------------------------

**Conclusion**
The Form library provides a flexible and powerful solution for creating customizable and reusable forms in Android applications. With built-in support for various form field types and the ability to add custom fields, developers can easily integrate complex forms into their apps. The library also simplifies the process of submitting form data to Google Sheets endpoints, making it an ideal choice for applications that require data collection and analysis.

We hope this documentation has provided a clear and comprehensive guide to using the Form library. By following the steps outlined, you can quickly set up and start using the library to enhance your Android projects. If you encounter any issues or have any questions, please refer to the troubleshooting section or reach out for support. Happy coding!

