package io.github.ovso.nrtest.api

import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.http.GET

interface ProfileService {
  @GET("/r/app_bind.json")
  fun profile(): Single<JsonElement>
}