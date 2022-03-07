# gradle7-scala-incr-compile-issue

Minimal multi-module Gradle Scala project running Gradle 7.4.  When the module2/Independent.scala is modified, the Zinc compiler invalidates the compiled module1/StringUtil.class and nested class, triggering a full recompilation of module2.
