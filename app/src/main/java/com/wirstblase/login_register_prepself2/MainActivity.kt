package com.wirstblase.login_register_prepself2

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


var userToken: String = " "

/*

var internalActivity1 = Activity

fun saveUserToken(token: String){
    val sharedPref = internalActivity1?.getPreferences(Context.MODE_PRIVATE) ?: return
    with (sharedPref.edit()) {
        putInt("MY_COINS", coins)
        apply()
    }
}*/

fun loadUserToken(){

}

class MainActivity : AppCompatActivity() {
    lateinit var switchToLoginBtn: Button
    lateinit var registerBtn: Button
    lateinit var firstNameField: EditText
    lateinit var lastNameField: EditText
    lateinit var emailAddrField: EditText
    lateinit var passWrdField: EditText
    lateinit var passWrdConfirmField: EditText
    lateinit var registerWelcomeText: TextView

    fun registerUser(firstName: String, lastName: String, emailAddr: String, passWrd: String): Int {
        val url = "https://api.prepself.razvan.store/api/v1/registration"

        val client = OkHttpClient()

        val JSON = MediaType.get("application/json; charset=utf-8")
        var content =
            "{\"firstName\":\"$firstName\", \"lastName\":\"$lastName\", \"email\":\"$emailAddr\", \"password\": \"$passWrd\"}"

        /*content = "{\n" + hard coded test
                "    \"firstName\": \"Bilal\",\n" +
                "    \"lastName\": \"Ahmed1\",\n" +
                "    \"email\": \"bilal.ahmed1@gmail.com\",\n" +
                "    \"password\": \"password\"\n" +
                "}"*/

        val body = RequestBody.create(
            JSON,
            content
        )

        println("REQUEST BODY:")
        println(content)

        val request = Request.Builder()
            //.addHeader("", "")
            .url(url)
            .post(body)
            .build()

        try {
            val thread = Thread(Runnable {
                //Thread.sleep(1000)
                val response = client.newCall(request).execute()
                println("API CALL RESPONSE:")
                println(response.request())
                userToken = response.body()!!.string()
                //println(userToken)
                //println(response.body()!!.string().javaClass.kotlin)
                //userToken = response.body()!!.string().toString()

            })

            thread.start()
            thread.join()

            /*while(userToken.equals(" ")){
                println("waiting boi")
            }*/

            println("USERTOKEN DEBUG:")
            println(userToken)
            println("USERTOKEN DEBUG END")
            if(userToken.contains("has already been taken"))
                //user already exists or is disabled
                return 0
            else
                return 1

        } catch (e: Exception) {
            println("there was an error with the api call!!!!")
            //print(e.stackTrace)
            e.printStackTrace()
            return 0
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstNameField = findViewById(R.id.editTextTextPersonName4)
        lastNameField = findViewById(R.id.editTextTextPersonName3)
        registerBtn = findViewById(R.id.button2)
        emailAddrField = findViewById(R.id.editTextTextEmailAddress2)
        passWrdField = findViewById(R.id.editTextTextPassword5)
        passWrdConfirmField = findViewById(R.id.editTextTextPassword6)
        switchToLoginBtn = findViewById(R.id.switchToLoginButton)
        registerWelcomeText = findViewById(R.id.registerWelcomeText)

        switchToLoginBtn.background.alpha = 0
        switchToLoginBtn.stateListAnimator = null
        switchToLoginBtn.setTextColor(Color.parseColor("#FFFFFF"))

        registerBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))

        registerWelcomeText.setTextColor(Color.parseColor("#FFFFFF"))

        switchToLoginBtn.setOnClickListener{

            startActivity(
                Intent(this@MainActivity, test::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

        }

        registerBtn.setOnClickListener {
            //string = editText.text.toString()
            //textView.text = string

            var firstName = firstNameField.text.toString()
            var lastName = lastNameField.text.toString()
            var emailAddr = emailAddrField.text.toString()
            var passWrd = passWrdField.text.toString()
            var passWrdConfirm = passWrdField.text.toString()

            var failed = 0
            if (firstName.length == 0) {
                failed = 1
            }
            if (lastName.length == 0) {
                failed = 1
            }
            if (emailAddr.length == 0) {
                failed = 1
            }
            if (passWrd.length < 8) {
                failed = 1
            }
            /*if(passWrd.compareTo(passWrdConfirm) != 0){
                failed = 1
            }*/

            if (failed == 0 || failed == 1) {
                var res = registerUser(firstName, lastName, emailAddr, passWrd)
                if (res == 1) {
                    //segue to the app
                    Toast.makeText(
                        getApplicationContext(),
                        "registration succeeded",
                        Toast.LENGTH_SHORT
                    ).show();
                } else {
                    //print e r r o r
                    Toast.makeText(getApplicationContext(), "error signing up", Toast.LENGTH_SHORT)
                        .show();
                }
            } else {
                //some error whO knows
                Toast.makeText(getApplicationContext(), "check credentials", Toast.LENGTH_SHORT)
                    .show();
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val focus: View? = currentFocus
        if (focus != null) {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            inputMethodManager.hideSoftInputFromWindow(focus.getWindowToken(), 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}