package io.github.ovso.nrtest.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRequest<T> {
  val api: T
    get() = createRetrofit().create(apiClass)

  protected abstract val apiClass: Class<T>

  private fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://static.wippy.io")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
  }

}