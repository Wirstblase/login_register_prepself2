package com.wirstblase.login_register_prepself2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wirstblase.login_register_prepself2.databinding.ActivityMainBinding
import com.wirstblase.login_register_prepself2.databinding.ActivityRecipeListBinding

class RecipeList : AppCompatActivity() {


    private lateinit var binding: ActivityRecipeListBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        binding= ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListeners()
    }




    fun clickListeners() {
        binding.fridgeRecipe.setOnClickListener(View.OnClickListener { openFridgeActivity() })
        binding.discoveryRecipe.setOnClickListener(View.OnClickListener { openDiscoveryActivity() })
        binding.checklistRecipe.setOnClickListener(View.OnClickListener { openChecklistActivity() })
        binding.userInfoRecipe.setOnClickListener(View.OnClickListener { openUserProfileActivity() })

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
    fun openDiscoveryActivity() {
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
        finish();
    }
}