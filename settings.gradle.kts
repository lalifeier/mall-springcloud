rootProject.name = "mall-springcloud"

pluginManagement {
  repositories {
    mavenLocal()
    maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenLocal()
    maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://developer.huawei.com/repo/")
    mavenCentral()
    gradlePluginPortal()
  }
}

fun File.autoIncludeModules(excludeDirs: Array<String> = emptyArray(), parentDirName: String = "") {
  val moduleSymbol = arrayOf("build.gradle", "build.gradle.kts")

  val ignoreDirs =
    arrayOf(
      "buildSrc",
      ".gradle",
      ".idea",
      ".vscode",
      ".git",
      ".github",
      ".devcontainer",
      "gradle",
      "bin",
      "build",
      "src"
    ) + excludeDirs

  listFiles()?.forEach { file ->
    if (file.isDirectory && !file.name.startsWith(".") && !ignoreDirs.contains(file.name)) {
      val subFiles = file.listFiles()
      val currentDirName = if (parentDirName.isNotBlank()) "$parentDirName:${file.name}" else file.name
      if (subFiles?.any { it.isFile && moduleSymbol.any { symbol -> it.name == symbol } } == true) {
        logger.lifecycle("Including module: $currentDirName")
        include(currentDirName)
      }
      file.autoIncludeModules(excludeDirs, currentDirName)
    }
  }
}

rootDir.autoIncludeModules(arrayOf("docs", "deploy", "docker", "style"))

gradle.settingsEvaluated {
  if (JavaVersion.current() < JavaVersion.VERSION_17) {
    throw GradleException("This build requires JDK 17. It's currently ${JavaVersion.current()}. You can ignore this check by passing '-Dorg.gradle.ignoreBuildJavaVersionCheck'.")
  }
}
