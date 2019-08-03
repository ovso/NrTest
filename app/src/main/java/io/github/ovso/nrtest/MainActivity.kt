package io.github.ovso.nrtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import io.github.ovso.nrtest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import kotlinx.android.synthetic.main.layout_profile_img.viewpager_profile

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val contentView: ActivityMainBinding =
      DataBindingUtil.setContentView(this, R.layout.activity_main)
    setupViewModel(contentView)
    setupActionBar()
    setupProfileImages();
    viewModel.fetchProfile()
  }

  private fun setupProfileImages() {
    viewModel.imagesLiveData.observe(this, Observer {
      it?.let {
        viewpager_profile.adapter = MyPageAdapter(it)
      }
    })
  }

  private fun setupViewModel(contentView: ActivityMainBinding) {
    viewModel = provideViewModel()
    contentView.viewModel = viewModel
  }

  private fun setupActionBar() {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.profile_title)
  }

  private fun provideViewModel() = ViewModelProviders.of(this).get(MainViewModel::class.java)

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.menu_voice_talk -> return true
      android.R.id.home -> finish()
    }
    return super.onOptionsItemSelected(item)
  }

  class MyPageAdapter(private val images: List<String>) : PagerAdapter() {

    override fun getCount(): Int {
      return images.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
      return view === o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
      //container.removeView(container);
      //super.destroyItem(container, position, object);
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
      val view = LayoutInflater.from(container.context)
        .inflate(R.layout.item_profile_img, container, false)
      Glide.with(view).load(images[position]).into(view as ImageView)
      container.addView(view)
      return view
    }
  }
}
