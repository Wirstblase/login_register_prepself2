package com.wirstblase.login_register_prepself2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ViewPagerAdapter (val recipes:List<Recipe> ):RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){


    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {


        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage= getThumbnail(recipe = recipes[position])
        holder.itemView.ivImage.setImageBitmap(curImage)
        holder.itemView.recipeID.setText("${recipes[position].id}")
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}