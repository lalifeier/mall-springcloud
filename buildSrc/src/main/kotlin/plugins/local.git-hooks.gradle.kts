plugins {
  id("com.star-zero.gradle.githook")
  id("ru.netris.commitlint")
}

githook {
  failOnMissingHooksDir = false
  createHooksDirIfNotExist = false
  hooks {
    register("pre-commit") {
      task = "spotlessCheck spotlessApply"
    }
//    register("commit-msg") {
//      task = "commitlint -Dmsgfile=$1"
//    }
  }
}

commitlint {
  enforceRefs = false
}
