scalaVersion := "3.3.1"
version := "1.0"

name := "rockthejvm"
organization := "com.rockthejvm"

libraryDependencies ++= Seq(
    "com.lihaoyi" %% "fansi" % "0.4.0",
    "org.scalatest" %% "scalatest" % "3.2.13" % Test
    )