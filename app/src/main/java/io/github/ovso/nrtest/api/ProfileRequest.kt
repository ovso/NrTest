package io.github.ovso.nrtest.api

import io.github.ovso.nrtest.api.model.Person
import io.reactivex.Single

class ProfileRequest : BaseRequest<ProfileService>() {

  override val apiClass: Class<ProfileService>
    get() = ProfileService::class.java

  fun profile(): Single<Person> {
    return api.profile()
  }

}