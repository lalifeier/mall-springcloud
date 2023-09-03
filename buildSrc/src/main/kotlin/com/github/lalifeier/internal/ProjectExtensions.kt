package com.github.lalifeier.internal

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

//import org.gradle.accessors.dm.LibrariesForLibs
internal val Project.libs: VersionCatalog
  get() = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

//internal val Project.libs get() = the<LibrariesForLibs>()

//internal val Project.libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs") as org.gradle.accessors.dm.LibrariesForLibs

internal fun Project.getVariable(name: String): String? {
  val value = System.getenv(name)
  if (value != null) {
    return value
  }
  val propName = name.replace('_', '.').lowercase()
  return findProperty(propName)?.toString()
}
