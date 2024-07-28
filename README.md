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

To add FormFlow to your Android project, you need to include it as a dependency using JitPack. Follow these steps to set it up:

**Add JitPack to your root build.gradle file:**

`allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}`

**Add the dependency to your module's build.gradle file:**

`implementation("com.github.lamiaomar:FormLibrary:2.1.0")`

`// Core
    implementation("androidx.core:core-ktx:1.10.1")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    
    // Retrofit with Moshi Converter
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    // Viewmodel and live data
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")`
