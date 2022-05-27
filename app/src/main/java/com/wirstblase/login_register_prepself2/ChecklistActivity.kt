package com.wirstblase.login_register_prepself2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wirstblase.login_register_prepself2.databinding.ActivityChecklistBinding
import com.wirstblase.login_register_prepself2.databinding.ActivityRecipeListBinding
import com.wirstblase.login_register_prepself2.databinding.ActivityUserProfileBinding

class ChecklistActivity : AppCompatActivity() {


    private lateinit var binding: ActivityChecklistBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)
        binding= ActivityChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListeners()
    }



    fun clickListeners() {
        binding.fridgeChecklist.setOnClickListener(View.OnClickListener { openFridgeActivity() })
        binding.cookingListChecklist.setOnClickListener(View.OnClickListener { openRecipeListActivity() })
        binding.discoveryChecklist.setOnClickListener(View.OnClickListener { openDiscoveryActivity() })
        binding.userInfoChecklist.setOnClickListener(View.OnClickListener { openUserProfileActivity() })
    }

    fun openRecipeListActivity() {
        val intent = Intent(this, RecipeList::class.java)
        startActivity(intent)

    }
    fun openUserProfileActivity() {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
    }

    fun openFridgeActivity() {
        val intent = Intent(this, Fridge::class.java)
        startActivity(intent)
    }
    fun openDiscoveryActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}