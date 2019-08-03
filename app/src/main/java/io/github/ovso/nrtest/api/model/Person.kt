package io.github.ovso.nrtest.api.model

data class Person(
  var age: String,
  var alcohol: String,
  var basic_occupation: String,
  var blood_type: String,
  var description: String,
  var distance: String,
  var education_level: String,
  var height: String,
  var is_verify_mobile: Boolean,
  var location: String,
  var name: String,
  var profile_images: List<String>,
  var religion: String,
  var smoke: String
)