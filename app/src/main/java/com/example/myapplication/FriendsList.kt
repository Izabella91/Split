package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FriendsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)

        val buttonClick = findViewById<Button>(R.id.AddFriends)
        buttonClick.setOnClickListener {
            val intent = Intent(this, AddFriend::class.java)
            startActivity(intent)
        }

    }
}