package com.kudasschan.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.title = "User Details"

        val txtName: TextView = findViewById(R.id.txt_name)
        val txtUsername: TextView = findViewById(R.id.txt_username)
        val txtCompany: TextView = findViewById(R.id.txt_company)
        val txtLocation: TextView = findViewById(R.id.txt_location)
        val txtRepository: TextView = findViewById(R.id.txt_repository)
        val txtFollowers: TextView = findViewById(R.id.txt_followers)
        val txtFollowing: TextView = findViewById(R.id.txt_following)
        val profilePict: ImageView = findViewById(R.id.profile_image)

        val dataProfileGithub = intent.getParcelableExtra<ItemData>("KEY") as ItemData
        txtName.text = dataProfileGithub.name
        txtUsername.text = dataProfileGithub.username
        txtCompany.text = dataProfileGithub.company
        txtLocation.text = dataProfileGithub.location
        txtRepository.text = dataProfileGithub.repository.toString()
        txtFollowers.text = dataProfileGithub.followers.toString()
        txtFollowing.text = dataProfileGithub.following.toString()
        dataProfileGithub.avatar?.let { profilePict.setImageResource(it) }
    }
}