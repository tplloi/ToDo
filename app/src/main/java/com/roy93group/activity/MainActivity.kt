package com.roy93group.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import com.google.android.material.navigation.NavigationView
import com.loitpcore.annotation.LogTag
import com.loitpcore.core.base.BaseFontActivity
import com.loitpcore.core.utilities.LImageUtil
import com.loitpcore.core.utilities.LScreenUtil
import com.loitpcore.core.utilities.LSocialUtil
import com.loitpcore.core.utilities.LStoreUtil
import com.roy93group.BuildConfig
import com.roy93group.R
import com.roy93group.fragment.HomeFragment
import com.roy93group.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_drawer_end.*
import kotlinx.android.synthetic.main.view_drawer_main.*
import kotlinx.android.synthetic.main.view_drawer_start.view.*

/**
 * Created by Loitp on 12.09.2022
 * Galaxy One company,
 * Vietnam
 * +840766040293
 * freuss47@gmail.com
 */
@LogTag("MainActivity")
class MainActivity : BaseFontActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun setLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navViewStart.setNavigationItemSelectedListener(this)
        drawerLayout.useCustomBehavior(Gravity.START)
        drawerLayout.useCustomBehavior(Gravity.END)

        // cover
        LImageUtil.load(
            context = this,
            any = getString(R.string.link_cover),
            imageView = navViewStart.getHeaderView(0).ivCover
        )

        tvAd.text = LStoreUtil.readTxtFromRawFolder(nameOfRawFile = R.raw.infor)
        tvAd.isVisible = BuildConfig.DEBUG

        switchHomeScreen()
    }

    private fun switchHomeScreen() {
        navViewStart.menu.performIdentifierAction(R.id.navHome, 0)
        navViewStart.menu.findItem(R.id.navHome).isChecked = true
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBaseBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBaseBackPressed()
                return
            }
            this.doubleBackToExitPressedOnce = true
            showShortInformation(msg = getString(R.string.press_again_to_exit), isTopAnchor = false)
            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)
        }
    }
//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            if (doubleBackToExitPressedOnce) {
//                super.onBackPressed()
//                return
//            }
//            this.doubleBackToExitPressedOnce = true
//            showShortInformation(getString(R.string.press_again_to_exit), isTopAnchor = false)
//            Handler(Looper.getMainLooper()).postDelayed({
//                doubleBackToExitPressedOnce = false
//            }, 2000)
//        }
//    }

    private var currentItemId = R.id.navHome
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navHome -> {
                currentItemId = R.id.navHome
                LScreenUtil.replaceFragment(
                    activity = this,
                    containerFrameLayoutIdRes = R.id.flContainer,
                    fragment = HomeFragment(),
                    isAddToBackStack = false
                )
            }
            R.id.navSetting -> {
                currentItemId = R.id.navSetting
                LScreenUtil.replaceFragment(
                    activity = this,
                    containerFrameLayoutIdRes = R.id.flContainer,
                    fragment = SettingFragment(),
                    isAddToBackStack = false
                )
            }
            R.id.navRateApp -> {
                LSocialUtil.rateApp(this)
            }
            R.id.navMoreApp -> {
                LSocialUtil.moreApp(this)
            }
            R.id.navPolicy -> {
                LSocialUtil.openBrowserPolicy(this)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        navViewStart.postDelayed({
            if (currentItemId == R.id.navHome) {
                navViewStart.menu.findItem(R.id.navHome).isChecked = true
            } else if (currentItemId == R.id.navSetting) {
                navViewStart.menu.findItem(R.id.navSetting).isChecked = true
            }
        }, 500)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_drawer_end, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionRightDrawer -> {
                drawerLayout.openDrawer(GravityCompat.END)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
