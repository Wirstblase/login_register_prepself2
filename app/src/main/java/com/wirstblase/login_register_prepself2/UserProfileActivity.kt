package com.wirstblase.login_register_prepself2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wirstblase.login_register_prepself2.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        binding= ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListeners()
    }

    fun clickListeners() {
        binding.fridgeUser.setOnClickListener(View.OnClickListener { openFridgeActivity() })
        binding.cookingListUser.setOnClickListener(View.OnClickListener { openRecipeListActivity() })
        binding.discoveryUser.setOnClickListener(View.OnClickListener { openDiscoveryActivity() })
        binding.checklistUser.setOnClickListener(View.OnClickListener { openChecklistActivity() })

    }



    fun openChecklistActivity() {
        val intent = Intent(this, ChecklistActivity::class.java)
        startActivity(intent)
        finish();
    }
    fun openDiscoveryActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
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
}