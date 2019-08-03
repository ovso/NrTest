package io.github.ovso.nrtest

import androidx.lifecycle.ViewModel
import io.github.ovso.nrtest.api.ProfileRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel : ViewModel() {
  private val compositeDisposable = CompositeDisposable()

  fun fetchProfile() {
    addDisposable(
      ProfileRequest().profile().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribeBy {
          Timber.d("json = $it")
        })
  }

  private fun addDisposable(d: Disposable) {
    compositeDisposable.add(d)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}