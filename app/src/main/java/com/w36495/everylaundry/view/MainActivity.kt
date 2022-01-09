package com.w36495.everylaundry.view

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.w36495.everylaundry.R
import com.w36495.everylaundry.databinding.ActivityMainBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MapFragment()).commit()

        binding.navBottom.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_map ->
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MapFragment()).commit()
            R.id.nav_favorite ->
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FavoriteFragment()).commit()
            R.id.nav_board ->
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, BoardFragment()).commit()
            R.id.nav_setting ->
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, SettingFragment()).commit()
        }
        return true
    }
}