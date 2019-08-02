package io.github.ovso.nrtest.api

import com.google.gson.JsonElement
import io.reactivex.Single

class ProfileRequest : BaseRequest<ProfileService>() {

  override val apiClass: Class<ProfileService>
    get() = ProfileService::class.java

  fun profile(): Single<JsonElement> {
    return api.profile()
  }

}