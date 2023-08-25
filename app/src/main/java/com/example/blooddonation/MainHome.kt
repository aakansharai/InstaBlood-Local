package com.example.blooddonation

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.navigation.NavigationView
import org.w3c.dom.Text

class MainHome : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout

    lateinit var builder : AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)


//====================   ALL ABOUT NAVIGATION   =========================

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = findViewById(R.id.Drawer)
        val navigation : NavigationView = findViewById(R.id.navigation)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navigation.setNavigationItemSelectedListener {

            it.isChecked = true     // highlighting the item which is selected

            when (it.itemId){

                R.id.profile -> replaceFragment(Profile(), it.title.toString())
                R.id.rewards -> replaceFragment(Rewards(), it.title.toString())
                R.id.RecordsOfDonation -> replaceFragment(DonationRecords(), it.title.toString())
                R.id.SignOut -> replaceFragment(SignOut(), it.title.toString())

            }
            true
        }


//====================   SWITCH TOGGLE   =========================

        val switch : SwitchCompat = findViewById(R.id.switchBtn)
        builder = AlertDialog.Builder(this)

        switch.setOnClickListener {
            if(switch.isChecked){
                builder
                    .setTitle("title")
                    .setMessage("Do you want to turn it On?")
                    .setNegativeButton("No") { _, _ ->
                        switch.isChecked = false
                        switch.text = "Not Available to Donate"
                        Toast.makeText(this, "You are Available to donate blood.", Toast.LENGTH_SHORT).show()
                    }
                    .setPositiveButton("Yes"){ _, _ ->
                        switch.isChecked = true
                        switch.text = "Available to Donate"
                    }.show()
            }
            else{
                builder
                    .setTitle("title")
                    .setMessage("Do you want to turn it Off?")
                    .setNegativeButton("No") { p0, p1 ->
                        switch.isChecked = true
                        switch.text = "Available to Donate"

                    }
                    .setPositiveButton("Yes"){ p0, p1 ->
                        switch.isChecked = false
                        switch.text = "Not Available to Donate"
                        Toast.makeText(this, "You are not Available to donate blood.", Toast.LENGTH_SHORT).show()
                    }.show()
            }
        }

    }

    private fun replaceFragment(fragment: Fragment, title: String){
        val fragmentManager = supportFragmentManager
        val fragmentTransection = fragmentManager.beginTransaction()
        fragmentTransection.replace(R.id.frameLayout, fragment)
        fragmentTransection.commit()

        drawerLayout.closeDrawers()
        setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}