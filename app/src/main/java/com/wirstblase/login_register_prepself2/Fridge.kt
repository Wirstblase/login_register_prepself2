package com.wirstblase.login_register_prepself2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wirstblase.login_register_prepself2.databinding.ActivityChecklistBinding
import com.wirstblase.login_register_prepself2.databinding.ActivityFridgeBinding

class Fridge : AppCompatActivity() {


    private lateinit var binding: ActivityFridgeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fridge)
        binding= ActivityFridgeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickListeners()
    }
    fun clickListeners() {
        binding.cookingListFridge.setOnClickListener(View.OnClickListener { openRecipeListActivity() })
        binding.discoveryFridge.setOnClickListener(View.OnClickListener { openDiscoveryActivity() })
        binding.checklistFridge.setOnClickListener(View.OnClickListener { openChecklistActivity() })
        binding.userInfoFridge.setOnClickListener(View.OnClickListener { openUserProfileActivity() })
    }

    fun openRecipeListActivity() {
        val intent = Intent(this, RecipeList::class.java)
        startActivity(intent)
        finish();
    }
    fun openUserProfileActivity() {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
        finish();
    }

    fun openChecklistActivity() {
        val intent = Intent(this, ChecklistActivity::class.java)
        startActivity(intent)
        finish();
    }
    fun openDiscoveryActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish();
    }
}