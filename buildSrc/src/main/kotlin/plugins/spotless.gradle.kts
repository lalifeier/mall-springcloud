plugins {
  id("com.diffplug.spotless")
}

spotless {
  java {
    // apply a specific flavor of google-java-format
    googleJavaFormat().aosp().reflowLongStrings()
    // fix formatting of type annotations
    formatAnnotations()
  }
}
