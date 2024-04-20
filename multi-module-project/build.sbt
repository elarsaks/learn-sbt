ThisBuild / scalaVersion := "3.3.1"
ThisBuild / version := "1.0.0"
ThisBuild / name := "multi-module"
ThisBuild / organization := "com.rockthejvm"

// Add external resolver
// resolvers += Resolver.url("my-test-repo", url("https://rockthejvm.com/repository/..."))

// add Maven local repo
// resolver += Resolver.mavenLocal 

// custom tasks
lazy val printerTask = taskKey[Unit]("Custom Printer Task")
printerTask := { // Binding code to task
    CustomTaskPrinter.print()
}

lazy val core = (project in file("core")).settings(
    assembly / mainClass := Some("com.rockthejvm.CoreApp"), 
    libraryDependencies += Constants.rootPackage %% "cats-effect" % "3.3.1"
)

lazy val server = (project in file("server")).dependsOn(core)

lazy val root = (project in file(".")).aggregate(core, server)
