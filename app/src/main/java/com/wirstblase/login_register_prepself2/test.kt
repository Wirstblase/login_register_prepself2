package com.wirstblase.login_register_prepself2

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View

class test : AppCompatActivity() {

    lateinit var test1: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        test1 = findViewById(R.id.view1)

        test1.background = roundedCornersDrawable(
            2.dpToPixels(applicationContext), // border width in pixels
            Color.parseColor("#58111A"), // border color
            30.dpToPixels(applicationContext).toFloat(), // corners radius
            Color.parseColor("#FFFFFF")
        )

        test1.background.alpha = 95

    }

    // extension function to get rounded corners border
    fun roundedCornersDrawable(
        borderWidth: Int = 10, // border width in pixels
        borderColor: Int = Color.BLACK, // border color
        cornerRadius: Float = 25F, // corner radius in pixels
        bgColor: Int = Color.TRANSPARENT // view background color
    ): Drawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setStroke(borderWidth, borderColor)
            setColor(bgColor)
            // make it rounded corners
            this.cornerRadius = cornerRadius
        }
    }


    // extension function to convert dp to equivalent pixels
    fun Int.dpToPixels(context: Context):Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
    ).toInt()
}