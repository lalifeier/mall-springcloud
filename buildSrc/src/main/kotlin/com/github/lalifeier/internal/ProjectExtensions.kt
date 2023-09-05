package com.github.lalifeier.internal

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

//internal val Project.libs get() = the<LibrariesForLibs>()
//internal val Project.libs get() = project.extensions.getByName("libs") as LibrariesForLibs

internal fun Project.getVariable(name: String): String? {
  val value = System.getenv(name)
  if (value != null) {
    return value
  }
  val propName = name.replace('_', '.').lowercase()
  return findProperty(propName)?.toString()
}

internal fun Project.version(key: String): String = extensions
  .getByType<VersionCatalogsExtension>()
  .named("libs")
  .findVersion(key)
  .get()
  .requiredVersion

internal fun Project.versionInt(key: String) = version(key).toInt()

internal val Project.PROTOBUF_VERSION get() = version("protobuf")
internal val Project.GRPC_VERSION get() = version("grpc")
internal val Project.GRPC_KOTLIN_VERSION get() = version("grpc-kotlin")
