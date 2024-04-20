val scala212 = "2.12.16"
val scala331 = "3.3.1"

ThisBuild / scalaVersion := scala331
ThisBuild / version := "1.0.0"
ThisBuild / name := "multi-module"
ThisBuild / organization := "com.rockthejvm"

// Add external resolver
ThisBuild / resolvers += Resolver.url("my-test-repo", url("https://rockthejvm.com/repository/..."))

// Add Maven local repo
ThisBuild / resolvers += Resolver.mavenLocal 

// Custom tasks
lazy val printerTask = taskKey[Unit]("Custom Printer Task")
printerTask := {
  val uuidTaskValue = uuidStringTask.value
  println(s"Generated uuid from task: $uuidTaskValue")

  val uuidSettingValue = uuidStringSetting.value
  println(s"Generated uuid from setting: $uuidSettingValue")

  CustomTaskPrinter.print()
}

lazy val uuidStringTask = taskKey[String]("Random UUID generator")
uuidStringTask := {
  StringTask.strTask()
}

// Custom settings
lazy val uuidStringSetting = settingKey[String]("Random UUID setting")
uuidStringSetting := {
  val uuid = StringTask.strTask()
  // Add some more code here if needed
  uuid
}

// command aliases
addCommandAlias("ci", "compile;test;assembly")

lazy val core = (project in file("core"))
  .settings(
    assembly / mainClass := Some("com.rockthejvm.CoreApp"), 
    libraryDependencies += Constants.rootPackage %% "cats-effect" % "3.3.1",
    crossScalaVersions := List(scala212, scala331)
  )

lazy val server = (project in file("server")).dependsOn(core)

lazy val root = (project in file(".")).aggregate(core, server)
