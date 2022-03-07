package com.kudasschan.github

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<ItemData>()
    private lateinit var rvList: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rv_list)
        rvList.setHasFixedSize(true)
        list.addAll(getListUser())
        showRecyclerList()
        rvList.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }

        setListClickAction()
    }

    private fun getListUser(): ArrayList<ItemData> {
        val txtName = resources.getStringArray(R.array.name)
        val txtUsername = resources.getStringArray(R.array.username)
        val profilePict = resources.obtainTypedArray(R.array.avatar)
        val txtCompany = resources.getStringArray(R.array.company)
        val txtLocation = resources.getStringArray(R.array.location)
        val txtRepository = resources.getStringArray(R.array.repository)
        val txtFollowers = resources.getStringArray(R.array.followers)
        val txtFollowing = resources.getStringArray(R.array.following)

        val listUser = ArrayList<ItemData>()
        for (position in txtName.indices) {
            val githubUser = ItemData(
                profilePict.getResourceId(position, -1),
                txtUsername[position],
                txtName[position],
                txtCompany[position],
                txtLocation[position],
                txtRepository[position],
                txtFollowers[position],
                txtFollowing[position]
            )
            listUser.add(githubUser)
        }
        return listUser
    }

    private fun showRecyclerList() {
        rvList.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(list)
        rvList.adapter = adapter
    }

    private fun setListClickAction() {
        adapter.setOnItemClickCallback(object : ItemAdapter.OnItemClickCallback {
            override fun onItemClick(data: ItemData) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("KEY", data)
                startActivity(intent)
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}