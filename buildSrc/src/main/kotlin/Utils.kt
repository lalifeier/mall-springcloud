import org.gradle.api.Project

fun Project.getVariable(name: String): String? {
  val value = System.getenv(name)
  if (value != null) {
    return value
  }
  val propName = name.replace('_', '.').toLowerCase()
  return findProperty(propName)?.toString()
}

