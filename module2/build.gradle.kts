plugins {
    `java-library`
    scala
}

dependencies {
    scalaCompilerPlugins("org.scalamacros:paradise_2.12.14:2.1.1")
    scalaCompilerPlugins("com.lihaoyi:acyclic_2.12:0.1.8")
    implementation("org.scala-lang:scala-library:2.12.14")
    implementation(project(":module1"))

    testImplementation("org.scalatest:scalatest_2.12:3.0.5")
}
