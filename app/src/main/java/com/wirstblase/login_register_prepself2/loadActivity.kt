package com.wirstblase.login_register_prepself2

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import jp.wasabeef.blurry.Blurry
import java.util.*
import kotlin.concurrent.schedule


class loadActivity : AppCompatActivity() {

    lateinit var loadBgImageView: ImageView
    lateinit var loadLogoImageView: ImageView
    lateinit var extraView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        loadBgImageView = findViewById(R.id.loadBgImageView)
        loadLogoImageView = findViewById(R.id.loadLogoImageView)
        extraView = findViewById(R.id.loadExtraView)

        val view: View = this.findViewById(android.R.id.content)
        val context = this@loadActivity

        //val rootView

        /*Blurry.with(context)
            .radius(10)
            .sampling(8)
            .color(Color.argb(66, 255, 255, 0))
            .async()
            .animate(500)
            .onto(view as ViewGroup?);*/
        //Blurry.with(context).radius(25).sampling(2).onto(loadBgImageView)
        //Blurry.with(context).capture(view).into(loadBgImageView)

        // Sync
        //val bitmap = Blurry.with(this)
        //  .radius(10)
        //  .sampling(8)
        //  .capture(loadBgImageView).get()
        //oadLogoImageView.setImageDrawable(BitmapDrawable(resources, bitmap))

        //loadBgImageView.setImageBitmap(bitmap)

        //val viewGroup = findViewById<View>(android.R.id.content) as ViewGroup
        //val viewGroup = findViewById<View>(R.id.loadBgImageView) as ViewGroup
        //viewGroup.getChildAt(0)
        //val extraViewGroup = findViewById<View>(R.id.loadExtraView).rootView as ViewGroup

        /*var extraViewGroup: ViewGroup = ViewGroup()
        if (extraViewGroup != null) {
            extraViewGroup.addView(extraView, 0)
        }*/

        loadLogoImageView.post(Runnable {
            /*Blurry.with(this@loadActivity)
                .radius(15)
                .sampling(6)
                .animate(2500)
                .onto()*/


            Blurry.with(context)
                .radius(15)
                .sampling(5)
                //.animate()
                //.async()
                .capture(view)
                .into(loadBgImageView)



            Timer().schedule(3000) {
                startActivity(
                    Intent(this@loadActivity, activity_login::class.java)/*,
                    ActivityOptions.makeSceneTransitionAnimation(context).toBundle()*/
                )
            }


        })

    }
}