package io.github.ovso.nrtest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import io.github.ovso.nrtest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_main.toolbar

class MainActivity : AppCompatActivity() {

  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val contentView =
      DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    viewModel = provideViewModel()
    contentView.viewModel = viewModel
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.profile_title)
    setupViewPager()
    viewModel.fetchProfile()
  }

  private fun setupViewPager() {
//    viewpager_main.adapter = MyPageAdapter()
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

  class MyPageAdapter : PagerAdapter() {

    override fun getCount(): Int {
      return 3
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
      return view === o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
      //container.removeView(container);
      //super.destroyItem(container, position, object);
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
      return super.instantiateItem(container, position)
    }
  }
}
