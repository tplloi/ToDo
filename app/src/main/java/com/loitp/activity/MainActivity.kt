package com.loitp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.annotation.LogTag
import com.core.base.BaseFontActivity
import com.core.common.Constants
import com.core.helper.adhelper.AdHelperActivity
import com.core.helper.donate.FrmDonate
import com.core.helper.gallery.GalleryCoreSplashActivity
import com.core.helper.mup.girl.ui.GirlSplashActivity
import com.core.utilities.*
import com.google.android.material.navigation.NavigationView
import com.loitp.BuildConfig
import com.loitp.R
import com.loitp.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_drawer_end.*
import kotlinx.android.synthetic.main.view_drawer_main.*
import kotlinx.android.synthetic.main.view_drawer_start.view.*

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
        LUIUtil.createAdBanner(adView)
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

        //cover
        LImageUtil.load(context = this, any = getString(R.string.link_cover), imageView = navViewStart.getHeaderView(0).ivCover)

        tvAd.text = LStoreUtil.readTxtFromRawFolder(nameOfRawFile = R.raw.ad)

        switchHomeScreen()
    }

    private fun switchHomeScreen() {
        navViewStart.menu.performIdentifierAction(R.id.navHome, 0)
        navViewStart.menu.findItem(R.id.navHome).isChecked = true
    }

    public override fun onPause() {
        adView.pause()
        super.onPause()
    }

    public override fun onResume() {
        adView.resume()
        super.onResume()
    }

    public override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
            this.doubleBackToExitPressedOnce = true
            showShortInformation(getString(R.string.press_again_to_exit))
            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)
        }
    }

    private var currentItemId = R.id.navHome
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navHome -> {
                logD("onNavigationItemSelected navHome")
                currentItemId = R.id.navHome
                LScreenUtil.replaceFragment(this, R.id.flContainer, HomeFragment(), false)
            }
            R.id.navGallery -> {
                if (BuildConfig.DEBUG) {
                    val intent = Intent(this, GalleryCoreSplashActivity::class.java)
                    intent.putExtra(Constants.AD_UNIT_ID_BANNER, getString(R.string.str_b))
                    intent.putExtra(Constants.BKG_SPLASH_SCREEN, getString(R.string.link_cover))
                    intent.putExtra(Constants.BKG_ROOT_VIEW, R.drawable.bkg_black)
                    //neu muon remove albumn nao thi cu pass id cua albumn do
                    val removeAlbumFlickrList = ArrayList<String>()
                    removeAlbumFlickrList.add(Constants.FLICKR_ID_STICKER)
                    intent.putStringArrayListExtra(Constants.KEY_REMOVE_ALBUM_FLICKR_LIST, removeAlbumFlickrList)
                    startActivity(intent)
                    LActivityUtil.tranIn(this)
                }
            }
            R.id.navGallery18 -> {
                if (BuildConfig.DEBUG) {
                    val intent = Intent(this, GirlSplashActivity::class.java)
                    intent.putExtra(Constants.AD_UNIT_ID_BANNER, getString(R.string.str_b))

                    val listBkg = ArrayList<String>()
                    listBkg.add("https://live.staticflickr.com/4657/26146170428_894243ab3c_b.jpg")
                    listBkg.add("https://live.staticflickr.com/4782/26128440717_a00e7cbec1_h.jpg")
                    listBkg.add("https://live.staticflickr.com/817/26128440867_1a90f7f8ec_h.jpg")
                    listBkg.add("https://live.staticflickr.com/789/26128436937_84ecab7cdf_h.jpg")
                    listBkg.add("https://live.staticflickr.com/4794/26128436737_69e5dfca7b_h.jpg")
                    intent.putExtra(Constants.BKG_SPLASH_SCREEN, listBkg.random())
                    startActivity(intent)
                    LActivityUtil.tranIn(this)
                }
            }
            R.id.navGallery18Feed -> {
                LSocialUtil.openUrlInBrowser(context = this, url = Constants.URL_GIRL)
            }
            R.id.navRateApp -> {
                LSocialUtil.rateApp(activity = this, packageName = packageName)
            }
            R.id.navMoreApp -> {
                LSocialUtil.moreApp(this)
            }
            R.id.navFacebookFanPage -> {
                LSocialUtil.likeFacebookFanpage(this)
            }
            R.id.navChatWithDev -> {
                LSocialUtil.chatMessenger(this)
            }
            R.id.navShareApp -> {
                LSocialUtil.shareApp(this)
            }
            R.id.navPolicy -> {
                LSocialUtil.openBrowserPolicy(context = this)
            }
            R.id.navAd -> {
                val intent = Intent(this, AdHelperActivity::class.java)
                intent.putExtra(Constants.AD_HELPER_IS_ENGLISH_LANGUAGE, true)
                startActivity(intent)
                LActivityUtil.tranIn(this)
            }
            R.id.navDonation -> {
                currentItemId = R.id.navDonation
                LScreenUtil.replaceFragment(activity = this, containerFrameLayoutIdRes = R.id.flContainer, fragment = FrmDonate(), isAddToBackStack = false)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        navViewStart.postDelayed({
            if (currentItemId == R.id.navHome) {
                navViewStart.menu.findItem(R.id.navHome).isChecked = true
            } else if (currentItemId == R.id.navDonation) {
                navViewStart.menu.findItem(R.id.navDonation).isChecked = true
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
