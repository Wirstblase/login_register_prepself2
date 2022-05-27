package com.wirstblase.login_register_prepself2

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class activity_login : AppCompatActivity() {

    lateinit var loginEmailField: TextView
    lateinit var loginPasswordField: TextView
    lateinit var loginLoginBtn: Button
    lateinit var loginWelcomeText: TextView
    lateinit var switchToRegisterBtn: Button

    fun loginUser(emailAddr: String, passWrd: String): Int {
        val url = "https://api.prepself.razvan.store/api/v1/login"

        val client = OkHttpClient()

        val JSON = MediaType.get("application/json; charset=utf-8")
        var content =
            "{\"email\":\"$emailAddr\", \"password\": \"$passWrd\"}"

        /*content = "{\n" + hard coded test
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

            //COPIED FROM REGISTER, MUST MODIFY v

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
        setContentView(R.layout.activity_login)

        loginEmailField = findViewById(R.id.loginEmailField)
        loginPasswordField = findViewById(R.id.loginPasswordField)
        loginLoginBtn = findViewById(R.id.loginLoginBtn)
        loginWelcomeText = findViewById(R.id.loginWelcomeText)
        switchToRegisterBtn = findViewById(R.id.switchToRegisterButton)

        switchToRegisterBtn.background.alpha = 0
        switchToRegisterBtn.stateListAnimator = null
        switchToRegisterBtn.setTextColor(Color.parseColor("#FFFFFF"))

        switchToRegisterBtn.setOnClickListener{

            startActivity(
                Intent(this@activity_login, MainScreenActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

        }

        loginLoginBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))

        loginWelcomeText.setTextColor(Color.parseColor("#FFFFFF"))

        loginLoginBtn.setOnClickListener{
            var emailAddr = loginEmailField.text.toString()
            var passWrd = loginPasswordField.text.toString()

            loginUser(emailAddr,passWrd)
        }

    }
}