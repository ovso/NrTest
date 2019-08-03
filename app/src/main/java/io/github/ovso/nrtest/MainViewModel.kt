package io.github.ovso.nrtest

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.ovso.nrtest.api.ProfileRequest
import io.github.ovso.nrtest.api.model.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
  private val compositeDisposable = CompositeDisposable()

  val imagesLiveData = MutableLiveData<List<String>>()
  val nameAgeObField = ObservableField<String>()
  val locDistObField = ObservableField<String>()
  val heiBlooObfield = ObservableField<String>()
  val verifMoObField = ObservableField<Int>()

  val educatiObField = ObservableField<String>()
  val occupatObField = ObservableField<String>()
  val descripObField = ObservableField<String>()

  val religioObField = ObservableField<String>()
  val alcoholObField = ObservableField<String>()
  val smokingObField = ObservableField<String>()

  fun fetchProfile() {
    addDisposable(
      ProfileRequest().profile().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribeBy {
          it?.let {
            handlePerson(it)
          }
        }
    )
  }

  private fun handlePerson(person: Person) {
    imagesLiveData.value = person.profile_images
    nameAgeObField.set("${person.name}, ${person.age}")
    locDistObField.set("${person.location}, ${person.distance}")
    heiBlooObfield.set("${person.height}, ${person.blood_type}")
    verifMoObField.set(if (person.is_verify_mobile) View.VISIBLE else View.INVISIBLE)

    educatiObField.set(person.education_level)
    occupatObField.set(person.basic_occupation)
    descripObField.set(person.description)

    religioObField.set(person.religion)
    alcoholObField.set(person.alcohol)
    smokingObField.set(person.smoke)
  }

  private fun addDisposable(d: Disposable) {
    compositeDisposable.add(d)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}

/*
{
    "name": "위피개발자",
    "profile_images": [
        "https://static.wippy.io/wodc/W4310e2f1b8202c7ed5e671883d5d0f488830d80a7ffdb9cadd902c1dd7bf0915_1800/",
        "https://static.wippy.io/wodc/W9e3dac67d984134f5c0c2d1b9f73209dbe1f9d6fa415017ef2149a5e00dd0ad6_1800/",
        "https://static.wippy.io/wodc/W9f5eaf59c4659ad93cbc5cfa68173edf33284b54fed25189b647c813d53acb88_1800/"
    ],
    "age": "25",
    "location": "서울시 강남구",
    "distance": "20km",
    "height": "174",
    "blood_type": "O형",
    "is_verify_mobile": false,
    "education_level": "대학교",
    "basic_occupation": "연구, 기술직",
    "description": "안녕하세요, 저는 위피 개발자입니다. 엔라이즈에서 위피와 모씨 앱을 만들고 있습니다. 잘 부탁 드립니다. :)",
    "religion": "무교",
    "alcohol": "자주",
    "smoke": "자주"
}
*/