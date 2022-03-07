plugins {
    java
    idea
}

group = "justinb99"
version = "1.0-SNAPSHOT"

idea {
    module {
        outputDir = file("build/classes/main")
        testOutputDir = file("build/classes/test")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
}

subprojects {
    apply<JavaPlugin>()
    apply<IdeaPlugin>()

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks {
        register("scalaTest", JavaExec::class.java) {
            dependsOn("testClasses")
            main = "org.scalatest.tools.Runner"
            args = listOf("-R", "build/classes/scala/test", "-oI")
            classpath = sourceSets.test.get().runtimeClasspath
        }

        withType<ScalaCompile>().configureEach {
            scalaCompileOptions.forkOptions.apply {
                memoryMaximumSize = "3G"
                jvmArgs = listOf("-Xss8M", "-XX:MaxMetaspaceSize=2048M")
            }
            scalaCompileOptions.additionalParameters = scalacAdditionalParams()
        }
    }
}

fun scalacAdditionalParams(): List<String> {
    val warnings: String = "0"

    val params = mutableListOf<String>(
        "-deprecation", // Emit warning and location for usages of deprecated APIs.
        "-encoding",
        "utf-8", // Specify character encoding used by source files.
        "-explaintypes", // Explain type errors in more detail.
        "-feature", // Emit warning and location for usages of features that should be imported explicitly.
        "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
        "-unchecked", // Enable additional warnings where generated code depends on assumptions.
        "-Xcheckinit", // Wrap field accessors to throw an exception on uninitialized access.
        "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
        "-Ypartial-unification", // Enable partial unification in type constructor inference
        "-P:acyclic:force"
    )

    if (warnings == "1") {
        params += listOf(
            "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
            "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
            "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
            "-Xlint:by-name-right-associative", // By-name parameter of right associative operator.
            "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
            "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
            "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
            "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
            "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
            "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
            "-Xlint:nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
            "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
            "-Xlint:option-implicit", // Option.apply used implicit view.
            "-Xlint:package-object-classes", // Class or object defined in package object.
            "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
            "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
            "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
            "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope.
            "-Xlint:unsound-match" // Pattern match may not be typesafe.
        )
    } else {
        params += listOf("-nowarn")
    }

    return params.toList()
}
