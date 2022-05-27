package com.wirstblase.login_register_prepself2

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.wirstblase.login_register_prepself2.databinding.ActivityMainScreenBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.item_view_pager.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

val client = OkHttpClient()
var token = "bc69f75f-2328-4af0-8147-79a055d58e2b"

class MainScreenActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        val MIN_DISTANCE: Int = 150
    }
    private lateinit var binding: ActivityMainScreenBinding

    private val mImageIds = arrayOf(R.drawable.balaur, R.drawable.img)


    private var initialLocationHorizontal = 0f
    private var endLocationHorizontal: Float = 0f
    private var initialLocationVertical = 0f
    private var endLocationVertical: Float = 0f
    private var imageView: ImageView? = null

    private val gestureDetector = GestureDetector(object : SimpleOnGestureListener() {
        override fun onLongPress(e: MotionEvent) {
            Toast.makeText(applicationContext, "long press", Toast.LENGTH_SHORT).show()
        }

        override fun onDoubleTap(e: MotionEvent): Boolean {
            Toast.makeText(applicationContext, "Double Tap", Toast.LENGTH_SHORT).show()
            return super.onDoubleTap(e)
        }
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        imageView = findViewById(R.id.viewPager)
//        with(imageView) { this?.setImageResource(mImageIds[0]) }
        test1 = findViewById(R.id.foodAttributeView)
        navigationButtonsView = findViewById(R.id.navigationButtonsView)

        test1.background = roundedCornersDrawable(
            2.dpToPixels(applicationContext), // border width in pixels
            Color.parseColor("#58111A"), // border color
            30.dpToPixels(applicationContext).toFloat(), // corners radius
            Color.parseColor("#FFFFFF")
        )

        navigationButtonsView.background = roundedCornersDrawable(
            2.dpToPixels(applicationContext),
            Color.parseColor("#FFFFFF"),
            30.dpToPixels(applicationContext).toFloat(),
            Color.parseColor("#FFFFFF")
        )

        test1.background.alpha = 95
        clickListeners()
        val adapter = ViewPagerAdapter(getRecipes()!!.body)
        viewPager.adapter = adapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

    }
//1 fridge
//2 recipes
//3 discorvery
//4

    fun clickListeners() {
        binding.fridgeMain.setOnClickListener(View.OnClickListener { openFridgeActivity() })
        binding.cookingListMain.setOnClickListener(View.OnClickListener { openRecipeListActivity() })
        binding.checklistMain.setOnClickListener(View.OnClickListener { openChecklistActivity() })
        binding.userInfoMain.setOnClickListener(View.OnClickListener { openUserProfileActivity() })

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initialLocationVertical = event.y
                initialLocationHorizontal = event.x
            }
            MotionEvent.ACTION_UP -> {
                endLocationHorizontal = event.x
                endLocationVertical = event.y
                val distanceHorizontal: Float = endLocationHorizontal - initialLocationHorizontal
                val distanceVertical: Float = endLocationVertical - initialLocationVertical
                if (distanceHorizontal > MIN_DISTANCE) {
                    Toast.makeText(this, "left -> right swipe", Toast.LENGTH_SHORT).show()
//                    openUserProfileActivity()
                } else if (distanceHorizontal < -MIN_DISTANCE) {
                    Toast.makeText(this, "right -> left swipe", Toast.LENGTH_SHORT).show()
//                    openChecklistActivity()
                }
                if(distanceVertical > MIN_DISTANCE) {
                    Toast.makeText(this, "up swipe", Toast.LENGTH_SHORT).show()
                } else if(distanceVertical < -MIN_DISTANCE) {
                    Toast.makeText(this, "down swipe", Toast.LENGTH_SHORT).show()
                    imageView?.setImageResource(mImageIds[1])
                } else {
                    println(distanceVertical)
                }
            }
        }
        return gestureDetector.onTouchEvent(event)
    }


    fun openChecklistActivity() {
        val intent = Intent(this, ChecklistActivity::class.java)
        startActivity(intent)
        finish();
    }
    fun openUserProfileActivity() {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
        finish();
    }

    fun openFridgeActivity() {
        val intent = Intent(this, Fridge::class.java)
        startActivity(intent)
        finish();
    }
    fun openRecipeListActivity() {
        val intent = Intent(this, RecipeList::class.java)
        startActivity(intent)
        finish();
    }
    lateinit var test1: View
    lateinit var navigationButtonsView: View



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

fun getRecipes(): RecipeResponse? {

    val url = "https://api.prepself.razvan.store/api/v1/recipes/list"
    val request = Request.Builder().url(url).addHeader("token", token).get().build()

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    var recipe: RecipeResponse? = null
    try {
        val thread = Thread {
            var response = client.newCall(request).execute()
            val jsonAdapter = moshi.adapter(RecipeResponse::class.java)
            recipe = jsonAdapter.fromJson(response.body()!!.string())

        }

        thread.start()
        thread.join()

    } catch (e: Exception) {
        println("there was an error with the api call!!!!")
        e.printStackTrace()
    }



    return recipe
}


fun getThumbnail(recipe: Recipe): Bitmap? {
    val url =
        "https://api.prepself.razvan.store/api/v1/recipes/images/thumbnail/?recipeId=${recipe.id}"
    val request = Request.Builder().url(url).addHeader("token", token).get().build()
    var bitMap: Bitmap?=null
    try {
        val thread = Thread {
            var response = client.newCall(request).execute()
            bitMap= BitmapFactory.decodeStream(response.body()?.byteStream())


            println(response.body()?.string())
        }

        thread.start()
        thread.join()

    } catch (e: Exception) {
        println("there was an error with the api call!!!!")
        e.printStackTrace()
    }
    return bitMap
}