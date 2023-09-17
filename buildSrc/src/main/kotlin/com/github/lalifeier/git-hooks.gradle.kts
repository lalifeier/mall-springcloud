package com.github.lalifeier

import gradle.kotlin.dsl.accessors._ca27ad621602daba75dffc91f3279d93.commitlint
import gradle.kotlin.dsl.accessors._ca27ad621602daba75dffc91f3279d93.githook

plugins {
  id("com.star-zero.gradle.githook")
  id("ru.netris.commitlint")
}

githook {
  failOnMissingHooksDir = false
  createHooksDirIfNotExist = false
  hooks {
    register("pre-commit") {
      task = "spotlessCheck"
    }
//    register("commit-msg") {
//      task = "commitlint -Dmsgfile=$1"
//    }
  }
}

commitlint {
  enforceRefs = false
}
