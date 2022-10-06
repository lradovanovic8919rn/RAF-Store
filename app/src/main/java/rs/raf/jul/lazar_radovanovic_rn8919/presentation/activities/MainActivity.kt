package rs.raf.jul.lazar_radovanovic_rn8919.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import rs.raf.jul.lazar_radovanovic_rn8919.R
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.adapter.TabAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPager = findViewById<ViewPager>(R.id.vp)
        viewPager.adapter = TabAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tl)
         tabLayout.setupWithViewPager(viewPager)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.cart -> startActivity(Intent(this,CartActivity::class.java))

        }
        return super.onOptionsItemSelected(item)
    }
}