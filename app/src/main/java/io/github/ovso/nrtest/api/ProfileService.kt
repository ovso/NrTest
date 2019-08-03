package io.github.ovso.nrtest.api

import io.github.ovso.nrtest.api.model.Person
import io.reactivex.Single
import retrofit2.http.GET

interface ProfileService {
  @GET("/r/app_bind.json")
  fun profile(): Single<Person>
}